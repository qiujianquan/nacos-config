// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mcp.proto

package com.fantasy.nacos.istio.model.mcp;

public interface MeshConfigResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:istio.mcp.v1alpha1.MeshConfigResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * The version of the response data.
   * </pre>
   *
   * <code>string version_info = 1;</code>
   * @return The versionInfo.
   */
  java.lang.String getVersionInfo();
  /**
   * <pre>
   * The version of the response data.
   * </pre>
   *
   * <code>string version_info = 1;</code>
   * @return The bytes for versionInfo.
   */
  com.google.protobuf.ByteString
      getVersionInfoBytes();

  /**
   * <pre>
   * The response resources wrapped in the common MCP *Resource*
   * message.
   * </pre>
   *
   * <code>repeated .istio.mcp.v1alpha1.Resource resources = 2;</code>
   */
  java.util.List<com.fantasy.nacos.istio.model.mcp.Resource>
      getResourcesList();
  /**
   * <pre>
   * The response resources wrapped in the common MCP *Resource*
   * message.
   * </pre>
   *
   * <code>repeated .istio.mcp.v1alpha1.Resource resources = 2;</code>
   */
  com.fantasy.nacos.istio.model.mcp.Resource getResources(int index);
  /**
   * <pre>
   * The response resources wrapped in the common MCP *Resource*
   * message.
   * </pre>
   *
   * <code>repeated .istio.mcp.v1alpha1.Resource resources = 2;</code>
   */
  int getResourcesCount();
  /**
   * <pre>
   * The response resources wrapped in the common MCP *Resource*
   * message.
   * </pre>
   *
   * <code>repeated .istio.mcp.v1alpha1.Resource resources = 2;</code>
   */
  java.util.List<? extends com.fantasy.nacos.istio.model.mcp.ResourceOrBuilder>
      getResourcesOrBuilderList();
  /**
   * <pre>
   * The response resources wrapped in the common MCP *Resource*
   * message.
   * </pre>
   *
   * <code>repeated .istio.mcp.v1alpha1.Resource resources = 2;</code>
   */
  com.fantasy.nacos.istio.model.mcp.ResourceOrBuilder getResourcesOrBuilder(
      int index);

  /**
   * <pre>
   * Type URL for resources wrapped in the provided resources(s). This
   * must be consistent with the type_url in the wrapper messages if
   * resources is non-empty.
   * </pre>
   *
   * <code>string type_url = 3;</code>
   * @return The typeUrl.
   */
  java.lang.String getTypeUrl();
  /**
   * <pre>
   * Type URL for resources wrapped in the provided resources(s). This
   * must be consistent with the type_url in the wrapper messages if
   * resources is non-empty.
   * </pre>
   *
   * <code>string type_url = 3;</code>
   * @return The bytes for typeUrl.
   */
  com.google.protobuf.ByteString
      getTypeUrlBytes();

  /**
   * <pre>
   * The nonce provides a way to explicitly ack a specific
   * MeshConfigResponse in a following MeshConfigRequest. Additional
   * messages may have been sent by client to the management server for
   * the previous version on the stream prior to this
   * MeshConfigResponse, that were unprocessed at response send
   * time. The nonce allows the management server to ignore any
   * further MeshConfigRequests for the previous version until a
   * MeshConfigRequest bearing the nonce.
   * </pre>
   *
   * <code>string nonce = 4;</code>
   * @return The nonce.
   */
  java.lang.String getNonce();
  /**
   * <pre>
   * The nonce provides a way to explicitly ack a specific
   * MeshConfigResponse in a following MeshConfigRequest. Additional
   * messages may have been sent by client to the management server for
   * the previous version on the stream prior to this
   * MeshConfigResponse, that were unprocessed at response send
   * time. The nonce allows the management server to ignore any
   * further MeshConfigRequests for the previous version until a
   * MeshConfigRequest bearing the nonce.
   * </pre>
   *
   * <code>string nonce = 4;</code>
   * @return The bytes for nonce.
   */
  com.google.protobuf.ByteString
      getNonceBytes();
}
