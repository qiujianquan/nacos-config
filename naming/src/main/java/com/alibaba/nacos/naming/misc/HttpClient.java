/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fantasy.nacos.naming.misc;

import com.fantasy.nacos.common.constant.HttpHeaderConsts;
import com.fantasy.nacos.common.utils.HttpMethod;
import com.fantasy.nacos.common.utils.IoUtils;
import com.fantasy.nacos.common.utils.VersionUtils;
import com.fantasy.nacos.core.utils.ApplicationUtils;
import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;

/**
 * Http Client.
 *
 * @author nacos
 */
public class HttpClient {

    private static final int TIME_OUT_MILLIS = 10000;

    private static final int CON_TIME_OUT_MILLIS = 5000;

    private static AsyncHttpClient asyncHttpClient;

    private static CloseableHttpClient postClient;

    static {
        AsyncHttpClientConfig.Builder builder = new AsyncHttpClientConfig.Builder();
        builder.setMaximumConnectionsTotal(-1);
        builder.setMaximumConnectionsPerHost(128);
        builder.setAllowPoolingConnection(true);
        builder.setFollowRedirects(false);
        builder.setIdleConnectionTimeoutInMs(TIME_OUT_MILLIS);
        builder.setConnectionTimeoutInMs(CON_TIME_OUT_MILLIS);
        builder.setCompressionEnabled(true);
        builder.setIOThreadMultiplier(1);
        builder.setMaxRequestRetry(0);
        builder.setUserAgent(UtilsAndCommons.SERVER_VERSION);

        asyncHttpClient = new AsyncHttpClient(builder.build());

        HttpClientBuilder builder2 = HttpClients.custom();
        builder2.setUserAgent(UtilsAndCommons.SERVER_VERSION);
        builder2.setConnectionTimeToLive(CON_TIME_OUT_MILLIS, TimeUnit.MILLISECONDS);
        builder2.setMaxConnPerRoute(-1);
        builder2.setMaxConnTotal(-1);
        builder2.disableAutomaticRetries();

        postClient = builder2.build();
    }

    /**
     * Request http delete method.
     *
     * @param url         url
     * @param headers     headers
     * @param paramValues params
     * @return {@link HttpResult} as response
     */
    public static HttpResult httpDelete(String url, List<String> headers, Map<String, String> paramValues) {
        return request(url, headers, paramValues, StringUtils.EMPTY, CON_TIME_OUT_MILLIS, TIME_OUT_MILLIS, "UTF-8",
                "DELETE");
    }

    /**
     * Request http get method.
     *
     * @param url         url
     * @param headers     headers
     * @param paramValues params
     * @return {@link HttpResult} as response
     */
    public static HttpResult httpGet(String url, List<String> headers, Map<String, String> paramValues) {
        return request(url, headers, paramValues, StringUtils.EMPTY, CON_TIME_OUT_MILLIS, TIME_OUT_MILLIS, "UTF-8",
                "GET");
    }

    /**
     * Do http request.
     *
     * @param url            request url
     * @param headers        request headers
     * @param paramValues    request params
     * @param body           request body
     * @param connectTimeout timeout of connection
     * @param readTimeout    timeout of request
     * @param encoding       charset of request
     * @param method         http method
     * @return {@link HttpResult} as response
     */
    public static HttpResult request(String url, List<String> headers, Map<String, String> paramValues, String body,
            int connectTimeout, int readTimeout, String encoding, String method) {
        HttpURLConnection conn = null;
        try {
            String encodedContent = encodingParams(paramValues, encoding);
            url += StringUtils.isBlank(encodedContent) ? StringUtils.EMPTY : ("?" + encodedContent);

            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setConnectTimeout(connectTimeout);
            conn.setReadTimeout(readTimeout);
            conn.setRequestMethod(method);

            setHeaders(conn, headers, encoding);

            if (StringUtils.isNotBlank(body)) {
                conn.setDoOutput(true);
                byte[] b = body.getBytes();
                conn.setRequestProperty("Content-Length", String.valueOf(b.length));
                conn.getOutputStream().write(b, 0, b.length);
                conn.getOutputStream().flush();
                conn.getOutputStream().close();
            }

            conn.connect();

            return getResult(conn);
        } catch (Exception e) {
            Loggers.SRV_LOG.warn("Exception while request: {}, caused: {}", url, e);
            return new HttpResult(500, e.toString(), Collections.<String, String>emptyMap());
        } finally {
            IoUtils.closeQuietly(conn);
        }
    }

    /**
     * Request http get method by async.
     *
     * @param url         url
     * @param headers     headers
     * @param paramValues params
     * @param handler     callback after request execute
     */
    public static void asyncHttpGet(String url, List<String> headers, Map<String, String> paramValues,
            AsyncCompletionHandler handler) throws Exception {
        asyncHttpRequest(url, headers, paramValues, handler, HttpMethod.GET);
    }

    /**
     * Request http post method by async.
     *
     * @param url         url
     * @param headers     headers
     * @param paramValues params
     * @param handler     callback after request execute
     */
    public static void asyncHttpPost(String url, List<String> headers, Map<String, String> paramValues,
            AsyncCompletionHandler handler) throws Exception {
        asyncHttpRequest(url, headers, paramValues, handler, HttpMethod.POST);
    }

    /**
     * Request http delete method by async.
     *
     * @param url         url
     * @param headers     headers
     * @param paramValues params
     * @param handler     callback after request execute
     */
    public static void asyncHttpDelete(String url, List<String> headers, Map<String, String> paramValues,
            AsyncCompletionHandler handler) throws Exception {
        asyncHttpRequest(url, headers, paramValues, handler, HttpMethod.DELETE);
    }

    /**
     * Do http request by async.
     *
     * @param url         request url
     * @param headers     request headers
     * @param paramValues request params
     * @param method      http method
     * @throws Exception exception when request
     */
    public static void asyncHttpRequest(String url, List<String> headers, Map<String, String> paramValues,
            AsyncCompletionHandler handler, String method) throws Exception {
        if (!MapUtils.isEmpty(paramValues)) {
            String encodedContent = encodingParams(paramValues, "UTF-8");
            url += (null == encodedContent) ? "" : ("?" + encodedContent);
        }

        AsyncHttpClient.BoundRequestBuilder builder;

        switch (method) {
            case HttpMethod.GET:
                builder = asyncHttpClient.prepareGet(url);
                break;
            case HttpMethod.POST:
                builder = asyncHttpClient.preparePost(url);
                break;
            case HttpMethod.PUT:
                builder = asyncHttpClient.preparePut(url);
                break;
            case HttpMethod.DELETE:
                builder = asyncHttpClient.prepareDelete(url);
                break;
            default:
                throw new RuntimeException("not supported method:" + method);
        }

        if (!CollectionUtils.isEmpty(headers)) {
            for (String header : headers) {
                builder.setHeader(header.split("=")[0], header.split("=")[1]);
            }
        }

        builder.setHeader("Accept-Charset", "UTF-8");

        if (handler != null) {
            builder.execute(handler);
        } else {
            builder.execute();
        }
    }

    /**
     * Request http post method by async with large body.
     *
     * @param url     url
     * @param headers headers
     * @param content full request content
     * @param handler callback after request execute
     */
    public static void asyncHttpPostLarge(String url, List<String> headers, String content,
            AsyncCompletionHandler handler) throws Exception {
        asyncHttpPostLarge(url, headers, content.getBytes(), handler);
    }

    /**
     * Request http post method by async with large body.
     *
     * @param url     url
     * @param headers headers
     * @param content full request content
     * @param handler callback after request execute
     */
    public static void asyncHttpPostLarge(String url, List<String> headers, byte[] content,
            AsyncCompletionHandler handler) throws Exception {
        AsyncHttpClient.BoundRequestBuilder builder = asyncHttpClient.preparePost(url);

        if (!CollectionUtils.isEmpty(headers)) {
            for (String header : headers) {
                builder.setHeader(header.split("=")[0], header.split("=")[1]);
            }
        }

        builder.setBody(content);

        builder.setHeader("Content-Type", "application/json; charset=UTF-8");
        builder.setHeader("Accept-Charset", "UTF-8");
        builder.setHeader("Accept-Encoding", "gzip");
        builder.setHeader("Content-Encoding", "gzip");

        if (handler != null) {
            builder.execute(handler);
        } else {
            builder.execute();
        }
    }

    /**
     * Request http delete method by async with large body.
     *
     * @param url     url
     * @param headers headers
     * @param content full request content
     * @param handler callback after request execute
     */
    public static void asyncHttpDeleteLarge(String url, List<String> headers, String content,
            AsyncCompletionHandler handler) throws Exception {
        AsyncHttpClient.BoundRequestBuilder builder = asyncHttpClient.prepareDelete(url);

        if (!CollectionUtils.isEmpty(headers)) {
            for (String header : headers) {
                builder.setHeader(header.split("=")[0], header.split("=")[1]);
            }
        }

        builder.setBody(content.getBytes());

        builder.setHeader("Content-Type", "application/json; charset=UTF-8");
        builder.setHeader("Accept-Charset", "UTF-8");
        builder.setHeader("Accept-Encoding", "gzip");
        builder.setHeader("Content-Encoding", "gzip");

        if (handler != null) {
            builder.execute(handler);
        } else {
            builder.execute();
        }
    }

    public static HttpResult httpPost(String url, List<String> headers, Map<String, String> paramValues) {
        return httpPost(url, headers, paramValues, "UTF-8");
    }

    /**
     * Request http post method.
     *
     * @param url         url
     * @param headers     headers
     * @param paramValues params
     * @param encoding    charset
     * @return {@link HttpResult} as response
     */
    public static HttpResult httpPost(String url, List<String> headers, Map<String, String> paramValues,
            String encoding) {
        try {

            HttpPost httpost = new HttpPost(url);

            RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000)
                    .setConnectTimeout(5000).setSocketTimeout(5000).setRedirectsEnabled(true).setMaxRedirects(5)
                    .build();
            httpost.setConfig(requestConfig);

            List<NameValuePair> nvps = new ArrayList<NameValuePair>();

            for (Map.Entry<String, String> entry : paramValues.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }

            httpost.setEntity(new UrlEncodedFormEntity(nvps, encoding));
            HttpResponse response = postClient.execute(httpost);
            HttpEntity entity = response.getEntity();

            String charset = encoding;
            if (entity.getContentType() != null) {

                HeaderElement[] headerElements = entity.getContentType().getElements();

                if (headerElements != null && headerElements.length > 0 && headerElements[0] != null
                        && headerElements[0].getParameterByName("charset") != null) {
                    charset = headerElements[0].getParameterByName("charset").getValue();
                }
            }

            return new HttpResult(response.getStatusLine().getStatusCode(),
                    IoUtils.toString(entity.getContent(), charset), Collections.<String, String>emptyMap());
        } catch (Throwable e) {
            return new HttpResult(500, e.toString(), Collections.<String, String>emptyMap());
        }
    }

    /**
     * Request http put method by async with large body.
     *
     * @param url     url
     * @param headers headers
     * @param content full request content
     * @param handler callback after request execute
     */
    public static void asyncHttpPutLarge(String url, Map<String, String> headers, byte[] content,
            AsyncCompletionHandler handler) throws Exception {
        AsyncHttpClient.BoundRequestBuilder builder = asyncHttpClient.preparePut(url);

        if (!headers.isEmpty()) {
            for (String headerKey : headers.keySet()) {
                builder.setHeader(headerKey, headers.get(headerKey));
            }
        }

        builder.setBody(content);

        builder.setHeader("Content-Type", "application/json; charset=UTF-8");
        builder.setHeader("Accept-Charset", "UTF-8");
        builder.setHeader("Accept-Encoding", "gzip");
        builder.setHeader("Content-Encoding", "gzip");

        if (handler != null) {
            builder.execute(handler);
        } else {
            builder.execute();
        }
    }

    /**
     * Request http get method by async with large body.
     *
     * @param url     url
     * @param headers headers
     * @param content full request content
     * @param handler callback after request execute
     */
    public static void asyncHttpGetLarge(String url, Map<String, String> headers, byte[] content,
            AsyncCompletionHandler handler) throws Exception {
        AsyncHttpClient.BoundRequestBuilder builder = asyncHttpClient.prepareGet(url);

        if (!headers.isEmpty()) {
            for (String headerKey : headers.keySet()) {
                builder.setHeader(headerKey, headers.get(headerKey));
            }
        }

        builder.setBody(content);

        builder.setHeader("Content-Type", "application/json; charset=UTF-8");
        builder.setHeader("Accept-Charset", "UTF-8");
        builder.setHeader("Accept-Encoding", "gzip");
        builder.setHeader("Content-Encoding", "gzip");

        if (handler != null) {
            builder.execute(handler);
        } else {
            builder.execute();
        }
    }

    /**
     * Request http put method with large body.
     *
     * @param url     url
     * @param headers headers
     * @param content full request content
     * @return {@link HttpResult} as response
     */
    public static HttpResult httpPutLarge(String url, Map<String, String> headers, byte[] content) {
        try {
            HttpClientBuilder builder = HttpClients.custom().setUserAgent(UtilsAndCommons.SERVER_VERSION)
                    .setConnectionTimeToLive(500, TimeUnit.MILLISECONDS);
            CloseableHttpClient httpClient = builder.build();

            HttpPut httpPut = new HttpPut(url);
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPut.setHeader(entry.getKey(), entry.getValue());
            }
            httpPut.setEntity(new ByteArrayEntity(content, ContentType.APPLICATION_JSON));

            HttpResponse response = httpClient.execute(httpPut);
            HttpEntity entity = response.getEntity();

            HeaderElement[] headerElements = entity.getContentType().getElements();
            String charset = headerElements[0].getParameterByName("charset").getValue();

            return new HttpResult(response.getStatusLine().getStatusCode(),
                    IoUtils.toString(entity.getContent(), charset), Collections.<String, String>emptyMap());
        } catch (Exception e) {
            return new HttpResult(500, e.toString(), Collections.<String, String>emptyMap());
        }
    }

    /**
     * Request http get method with large body.
     *
     * @param url     url
     * @param headers headers
     * @param content full request content
     * @return {@link HttpResult} as response
     */
    public static HttpResult httpGetLarge(String url, Map<String, String> headers, String content) {

        try {
            HttpClientBuilder builder = HttpClients.custom();
            builder.setUserAgent(UtilsAndCommons.SERVER_VERSION);
            builder.setConnectionTimeToLive(500, TimeUnit.MILLISECONDS);

            HttpGetWithEntity httpGetWithEntity = new HttpGetWithEntity();
            httpGetWithEntity.setURI(new URI(url));

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpGetWithEntity.setHeader(entry.getKey(), entry.getValue());
            }

            httpGetWithEntity.setEntity(new StringEntity(content, ContentType.create("application/json", "UTF-8")));
            CloseableHttpClient httpClient = builder.build();
            HttpResponse response = httpClient.execute(httpGetWithEntity);
            HttpEntity entity = response.getEntity();

            HeaderElement[] headerElements = entity.getContentType().getElements();
            String charset = headerElements[0].getParameterByName("charset").getValue();

            return new HttpResult(response.getStatusLine().getStatusCode(),
                    IoUtils.toString(entity.getContent(), charset), Collections.<String, String>emptyMap());
        } catch (Exception e) {
            return new HttpResult(500, e.toString(), Collections.<String, String>emptyMap());
        }
    }

    /**
     * Request http post method with large body.
     *
     * @param url     url
     * @param headers headers
     * @param content full request content
     * @return {@link HttpResult} as response
     */
    public static HttpResult httpPostLarge(String url, Map<String, String> headers, String content) {
        try {
            HttpClientBuilder builder = HttpClients.custom();
            builder.setUserAgent(UtilsAndCommons.SERVER_VERSION);
            builder.setConnectionTimeToLive(500, TimeUnit.MILLISECONDS);

            CloseableHttpClient httpClient = builder.build();
            HttpPost httpost = new HttpPost(url);

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpost.setHeader(entry.getKey(), entry.getValue());
            }

            httpost.setEntity(new StringEntity(content, ContentType.create("application/json", "UTF-8")));
            HttpResponse response = httpClient.execute(httpost);
            HttpEntity entity = response.getEntity();

            HeaderElement[] headerElements = entity.getContentType().getElements();
            String charset = headerElements[0].getParameterByName("charset").getValue();

            return new HttpResult(response.getStatusLine().getStatusCode(),
                    IoUtils.toString(entity.getContent(), charset), Collections.<String, String>emptyMap());
        } catch (Exception e) {
            return new HttpResult(500, e.toString(), Collections.<String, String>emptyMap());
        }
    }

    private static HttpResult getResult(HttpURLConnection conn) throws IOException {
        int respCode = conn.getResponseCode();

        InputStream inputStream;
        if (HttpURLConnection.HTTP_OK == respCode) {
            inputStream = conn.getInputStream();
        } else {
            inputStream = conn.getErrorStream();
        }

        Map<String, String> respHeaders = new HashMap<String, String>(conn.getHeaderFields().size());
        for (Map.Entry<String, List<String>> entry : conn.getHeaderFields().entrySet()) {
            respHeaders.put(entry.getKey(), entry.getValue().get(0));
        }

        String gzipEncoding = "gzip";

        if (gzipEncoding.equals(respHeaders.get(HttpHeaders.CONTENT_ENCODING))) {
            inputStream = new GZIPInputStream(inputStream);
        }

        return new HttpResult(respCode, IoUtils.toString(inputStream, getCharset(conn)), respHeaders);
    }

    private static String getCharset(HttpURLConnection conn) {
        String contentType = conn.getContentType();
        if (StringUtils.isEmpty(contentType)) {
            return "UTF-8";
        }

        String[] values = contentType.split(";");
        if (values.length == 0) {
            return "UTF-8";
        }

        String charset = "UTF-8";
        for (String value : values) {
            value = value.trim();

            if (value.toLowerCase().startsWith("charset=")) {
                charset = value.substring("charset=".length());
            }
        }

        return charset;
    }

    private static void setHeaders(HttpURLConnection conn, List<String> headers, String encoding) {
        if (null != headers) {
            for (Iterator<String> iter = headers.iterator(); iter.hasNext(); ) {
                conn.addRequestProperty(iter.next(), iter.next());
            }
        }

        conn.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + encoding);
        conn.addRequestProperty("Accept-Charset", encoding);
        conn.addRequestProperty(HttpHeaderConsts.CLIENT_VERSION_HEADER, VersionUtils.version);
        conn.addRequestProperty(HttpHeaderConsts.USER_AGENT_HEADER, UtilsAndCommons.SERVER_VERSION);
        conn.addRequestProperty(HttpHeaderConsts.REQUEST_SOURCE_HEADER, ApplicationUtils.getLocalAddress());
    }

    /**
     * Encoding parameters.
     *
     * @param params   parameters
     * @param encoding charset
     * @return parameters string
     * @throws UnsupportedEncodingException unsupported encodin exception
     */
    public static String encodingParams(Map<String, String> params, String encoding)
            throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        if (null == params || params.isEmpty()) {
            return null;
        }

        params.put("encoding", encoding);
        params.put("nofix", "1");

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (StringUtils.isEmpty(entry.getValue())) {
                continue;
            }

            sb.append(entry.getKey()).append("=");
            sb.append(URLEncoder.encode(entry.getValue(), encoding));
            sb.append("&");
        }

        return sb.toString();
    }

    /**
     * Translate parameter map.
     *
     * @param parameterMap parameter map
     * @return new parameter
     */
    public static Map<String, String> translateParameterMap(Map<String, String[]> parameterMap) {

        Map<String, String> map = new HashMap<>(16);
        for (String key : parameterMap.keySet()) {
            map.put(key, parameterMap.get(key)[0]);
        }
        return map;
    }

    public static class HttpResult {

        public final int code;

        public final String content;

        private final Map<String, String> respHeaders;

        public HttpResult(int code, String content, Map<String, String> respHeaders) {
            this.code = code;
            this.content = content;
            this.respHeaders = respHeaders;
        }

        public String getHeader(String name) {
            return respHeaders.get(name);
        }
    }

    public static class HttpGetWithEntity extends HttpEntityEnclosingRequestBase {

        public static final String METHOD_NAME = "GET";

        @Override
        public String getMethod() {
            return METHOD_NAME;
        }
    }
}
