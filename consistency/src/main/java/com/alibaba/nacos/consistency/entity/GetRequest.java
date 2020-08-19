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

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Data.proto

package com.fantasy.nacos.consistency.entity;

/**
 * Protobuf type {@code GetRequest}.
 */
@SuppressWarnings("all")
public final class GetRequest extends com.google.protobuf.GeneratedMessageV3 implements
        // @@protoc_insertion_point(message_implements:GetRequest)
        GetRequestOrBuilder {

    public static final int GROUP_FIELD_NUMBER = 1;

    public static final int DATA_FIELD_NUMBER = 2;

    public static final int EXTENDINFO_FIELD_NUMBER = 3;

    private static final long serialVersionUID = 0L;

    // @@protoc_insertion_point(class_scope:GetRequest)
    private static final GetRequest DEFAULT_INSTANCE;

    private static final com.google.protobuf.Parser<GetRequest> PARSER = new com.google.protobuf.AbstractParser<GetRequest>() {
        @Override
        public GetRequest parsePartialFrom(com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws com.google.protobuf.InvalidProtocolBufferException {
            return new GetRequest(input, extensionRegistry);
        }
    };

    static {
        DEFAULT_INSTANCE = new GetRequest();
    }

    private volatile Object group_;

    private com.google.protobuf.ByteString data_;

    private com.google.protobuf.MapField<String, String> extendInfo_;

    private byte memoizedIsInitialized = -1;

    // Use GetRequest.newBuilder() to construct.
    private GetRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
        super(builder);
    }

    private GetRequest() {
        group_ = "";
        data_ = com.google.protobuf.ByteString.EMPTY;
    }

    private GetRequest(com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        int mutable_bitField0_ = 0;
        com.google.protobuf.UnknownFieldSet.Builder unknownFields = com.google.protobuf.UnknownFieldSet.newBuilder();
        try {
            boolean done = false;
            while (!done) {
                int tag = input.readTag();
                switch (tag) {
                    case 0:
                        done = true;
                        break;
                    case 10: {
                        String s = input.readStringRequireUtf8();

                        group_ = s;
                        break;
                    }
                    case 18: {

                        data_ = input.readBytes();
                        break;
                    }
                    case 26: {
                        if (!((mutable_bitField0_ & 0x00000001) != 0)) {
                            extendInfo_ = com.google.protobuf.MapField
                                    .newMapField(ExtendInfoDefaultEntryHolder.defaultEntry);
                            mutable_bitField0_ |= 0x00000001;
                        }
                        com.google.protobuf.MapEntry<String, String> extendInfo__ = input
                                .readMessage(ExtendInfoDefaultEntryHolder.defaultEntry.getParserForType(),
                                        extensionRegistry);
                        extendInfo_.getMutableMap().put(extendInfo__.getKey(), extendInfo__.getValue());
                        break;
                    }
                    default: {
                        if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                            done = true;
                        }
                        break;
                    }
                }
            }
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        } catch (java.io.IOException e) {
            throw new com.google.protobuf.InvalidProtocolBufferException(e).setUnfinishedMessage(this);
        } finally {
            this.unknownFields = unknownFields.build();
            makeExtensionsImmutable();
        }
    }

    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
        return Data.internal_static_GetRequest_descriptor;
    }

    public static GetRequest parseFrom(java.nio.ByteBuffer data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static GetRequest parseFrom(java.nio.ByteBuffer data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static GetRequest parseFrom(com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static GetRequest parseFrom(com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static GetRequest parseFrom(byte[] data) throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static GetRequest parseFrom(byte[] data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static GetRequest parseFrom(java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static GetRequest parseFrom(java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static GetRequest parseDelimitedFrom(java.io.InputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static GetRequest parseDelimitedFrom(java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static GetRequest parseFrom(com.google.protobuf.CodedInputStream input) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static GetRequest parseFrom(com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
        return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(GetRequest prototype) {
        return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }

    public static GetRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static com.google.protobuf.Parser<GetRequest> parser() {
        return PARSER;
    }

    @Override
    @SuppressWarnings({"unused"})
    protected Object newInstance(UnusedPrivateParameter unused) {
        return new GetRequest();
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    @SuppressWarnings({"rawtypes"})
    @Override
    protected com.google.protobuf.MapField internalGetMapField(int number) {
        switch (number) {
            case 3:
                return internalGetExtendInfo();
            default:
                throw new RuntimeException("Invalid map field number: " + number);
        }
    }

    @Override
    protected FieldAccessorTable internalGetFieldAccessorTable() {
        return Data.internal_static_GetRequest_fieldAccessorTable
                .ensureFieldAccessorsInitialized(GetRequest.class, GetRequest.Builder.class);
    }

    /**
     * <code>string group = 1;</code>
     */
    public String getGroup() {
        Object ref = group_;
        if (ref instanceof String) {
            return (String) ref;
        } else {
            com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
            String s = bs.toStringUtf8();
            group_ = s;
            return s;
        }
    }

    /**
     * <code>string group = 1;</code>
     */
    public com.google.protobuf.ByteString getGroupBytes() {
        Object ref = group_;
        if (ref instanceof String) {
            com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8((String) ref);
            group_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    /**
     * <code>bytes data = 2;</code>
     */
    public com.google.protobuf.ByteString getData() {
        return data_;
    }

    private com.google.protobuf.MapField<String, String> internalGetExtendInfo() {
        if (extendInfo_ == null) {
            return com.google.protobuf.MapField.emptyMapField(ExtendInfoDefaultEntryHolder.defaultEntry);
        }
        return extendInfo_;
    }

    public int getExtendInfoCount() {
        return internalGetExtendInfo().getMap().size();
    }

    /**
     * <code>map&lt;string, string&gt; extendInfo = 3;</code>
     */

    public boolean containsExtendInfo(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return internalGetExtendInfo().getMap().containsKey(key);
    }

    /**
     * Use {@link #getExtendInfoMap()} instead.
     */
    @Deprecated
    public java.util.Map<String, String> getExtendInfo() {
        return getExtendInfoMap();
    }

    /**
     * <code>map&lt;string, string&gt; extendInfo = 3;</code>
     */

    public java.util.Map<String, String> getExtendInfoMap() {
        return internalGetExtendInfo().getMap();
    }

    /**
     * <code>map&lt;string, string&gt; extendInfo = 3;</code>
     */

    public String getExtendInfoOrDefault(String key, String defaultValue) {
        if (key == null) {
            throw new NullPointerException();
        }
        java.util.Map<String, String> map = internalGetExtendInfo().getMap();
        return map.containsKey(key) ? map.get(key) : defaultValue;
    }

    /**
     * <code>map&lt;string, string&gt; extendInfo = 3;</code>
     */

    public String getExtendInfoOrThrow(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        java.util.Map<String, String> map = internalGetExtendInfo().getMap();
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException();
        }
        return map.get(key);
    }

    @Override
    public final boolean isInitialized() {
        byte isInitialized = memoizedIsInitialized;
        if (isInitialized == 1) {
            return true;
        }
        if (isInitialized == 0) {
            return false;
        }

        memoizedIsInitialized = 1;
        return true;
    }

    @Override
    public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
        if (!getGroupBytes().isEmpty()) {
            com.google.protobuf.GeneratedMessageV3.writeString(output, 1, group_);
        }
        if (!data_.isEmpty()) {
            output.writeBytes(2, data_);
        }
        com.google.protobuf.GeneratedMessageV3
                .serializeStringMapTo(output, internalGetExtendInfo(), ExtendInfoDefaultEntryHolder.defaultEntry, 3);
        unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
        int size = memoizedSize;
        if (size != -1) {
            return size;
        }

        size = 0;
        if (!getGroupBytes().isEmpty()) {
            size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, group_);
        }
        if (!data_.isEmpty()) {
            size += com.google.protobuf.CodedOutputStream.computeBytesSize(2, data_);
        }
        for (java.util.Map.Entry<String, String> entry : internalGetExtendInfo().getMap().entrySet()) {
            com.google.protobuf.MapEntry<String, String> extendInfo__ = ExtendInfoDefaultEntryHolder.defaultEntry
                    .newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build();
            size += com.google.protobuf.CodedOutputStream.computeMessageSize(3, extendInfo__);
        }
        size += unknownFields.getSerializedSize();
        memoizedSize = size;
        return size;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GetRequest)) {
            return super.equals(obj);
        }
        GetRequest other = (GetRequest) obj;

        if (!getGroup().equals(other.getGroup())) {
            return false;
        }
        if (!getData().equals(other.getData())) {
            return false;
        }
        if (!internalGetExtendInfo().equals(other.internalGetExtendInfo())) {
            return false;
        }
        if (!unknownFields.equals(other.unknownFields)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        if (memoizedHashCode != 0) {
            return memoizedHashCode;
        }
        int hash = 41;
        hash = (19 * hash) + getDescriptor().hashCode();
        hash = (37 * hash) + GROUP_FIELD_NUMBER;
        hash = (53 * hash) + getGroup().hashCode();
        hash = (37 * hash) + DATA_FIELD_NUMBER;
        hash = (53 * hash) + getData().hashCode();
        if (!internalGetExtendInfo().getMap().isEmpty()) {
            hash = (37 * hash) + EXTENDINFO_FIELD_NUMBER;
            hash = (53 * hash) + internalGetExtendInfo().hashCode();
        }
        hash = (29 * hash) + unknownFields.hashCode();
        memoizedHashCode = hash;
        return hash;
    }

    @Override
    public Builder newBuilderForType() {
        return newBuilder();
    }

    @Override
    public Builder toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    @Override
    public com.google.protobuf.Parser<GetRequest> getParserForType() {
        return PARSER;
    }

    @Override
    public GetRequest getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    private static final class ExtendInfoDefaultEntryHolder {

        static final com.google.protobuf.MapEntry<String, String> defaultEntry = com.google.protobuf.MapEntry.<String, String>newDefaultInstance(
                Data.internal_static_GetRequest_ExtendInfoEntry_descriptor,
                com.google.protobuf.WireFormat.FieldType.STRING, "", com.google.protobuf.WireFormat.FieldType.STRING,
                "");
    }

    /**
     * Protobuf type {@code GetRequest}
     */
    public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
            // @@protoc_insertion_point(builder_implements:GetRequest)
            GetRequestOrBuilder {

        private int bitField0_;

        private Object group_ = "";

        private com.google.protobuf.ByteString data_ = com.google.protobuf.ByteString.EMPTY;

        private com.google.protobuf.MapField<String, String> extendInfo_;

        // Construct using com.fantasy.nacos.consistency.entity.GetRequest.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
            return Data.internal_static_GetRequest_descriptor;
        }

        @SuppressWarnings({"rawtypes"})
        protected com.google.protobuf.MapField internalGetMapField(int number) {
            switch (number) {
                case 3:
                    return internalGetExtendInfo();
                default:
                    throw new RuntimeException("Invalid map field number: " + number);
            }
        }

        @SuppressWarnings({"rawtypes"})
        protected com.google.protobuf.MapField internalGetMutableMapField(int number) {
            switch (number) {
                case 3:
                    return internalGetMutableExtendInfo();
                default:
                    throw new RuntimeException("Invalid map field number: " + number);
            }
        }

        @Override
        protected FieldAccessorTable internalGetFieldAccessorTable() {
            return Data.internal_static_GetRequest_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(GetRequest.class, GetRequest.Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders) {
            }
        }

        @Override
        public Builder clear() {
            super.clear();
            group_ = "";

            data_ = com.google.protobuf.ByteString.EMPTY;

            internalGetMutableExtendInfo().clear();
            return this;
        }

        @Override
        public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
            return Data.internal_static_GetRequest_descriptor;
        }

        @Override
        public GetRequest getDefaultInstanceForType() {
            return GetRequest.getDefaultInstance();
        }

        @Override
        public GetRequest build() {
            GetRequest result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        @Override
        public GetRequest buildPartial() {
            GetRequest result = new GetRequest(this);
            int from_bitField0_ = bitField0_;
            result.group_ = group_;
            result.data_ = data_;
            result.extendInfo_ = internalGetExtendInfo();
            result.extendInfo_.makeImmutable();
            onBuilt();
            return result;
        }

        @Override
        public Builder clone() {
            return super.clone();
        }

        @Override
        public Builder setField(com.google.protobuf.Descriptors.FieldDescriptor field, Object value) {
            return super.setField(field, value);
        }

        @Override
        public Builder clearField(com.google.protobuf.Descriptors.FieldDescriptor field) {
            return super.clearField(field);
        }

        @Override
        public Builder clearOneof(com.google.protobuf.Descriptors.OneofDescriptor oneof) {
            return super.clearOneof(oneof);
        }

        @Override
        public Builder setRepeatedField(com.google.protobuf.Descriptors.FieldDescriptor field, int index,
                Object value) {
            return super.setRepeatedField(field, index, value);
        }

        @Override
        public Builder addRepeatedField(com.google.protobuf.Descriptors.FieldDescriptor field, Object value) {
            return super.addRepeatedField(field, value);
        }

        @Override
        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof GetRequest) {
                return mergeFrom((GetRequest) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(GetRequest other) {
            if (other == GetRequest.getDefaultInstance()) {
                return this;
            }
            if (!other.getGroup().isEmpty()) {
                group_ = other.group_;
                onChanged();
            }
            if (other.getData() != com.google.protobuf.ByteString.EMPTY) {
                setData(other.getData());
            }
            internalGetMutableExtendInfo().mergeFrom(other.internalGetExtendInfo());
            this.mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        @Override
        public final boolean isInitialized() {
            return true;
        }

        @Override
        public Builder mergeFrom(com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
            GetRequest parsedMessage = null;
            try {
                parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                parsedMessage = (GetRequest) e.getUnfinishedMessage();
                throw e.unwrapIOException();
            } finally {
                if (parsedMessage != null) {
                    mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        /**
         * <code>string group = 1;</code>
         */
        public String getGroup() {
            Object ref = group_;
            if (!(ref instanceof String)) {
                com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
                String s = bs.toStringUtf8();
                group_ = s;
                return s;
            } else {
                return (String) ref;
            }
        }

        /**
         * <code>string group = 1;</code>
         */
        public Builder setGroup(String value) {
            if (value == null) {
                throw new NullPointerException();
            }

            group_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>string group = 1;</code>
         */
        public com.google.protobuf.ByteString getGroupBytes() {
            Object ref = group_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8((String) ref);
                group_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>string group = 1;</code>
         */
        public Builder setGroupBytes(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            checkByteStringIsUtf8(value);

            group_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>string group = 1;</code>
         */
        public Builder clearGroup() {

            group_ = getDefaultInstance().getGroup();
            onChanged();
            return this;
        }

        /**
         * <code>bytes data = 2;</code>
         */
        public com.google.protobuf.ByteString getData() {
            return data_;
        }

        /**
         * <code>bytes data = 2;</code>
         */
        public Builder setData(com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }

            data_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>bytes data = 2;</code>
         */
        public Builder clearData() {

            data_ = getDefaultInstance().getData();
            onChanged();
            return this;
        }

        private com.google.protobuf.MapField<String, String> internalGetExtendInfo() {
            if (extendInfo_ == null) {
                return com.google.protobuf.MapField.emptyMapField(ExtendInfoDefaultEntryHolder.defaultEntry);
            }
            return extendInfo_;
        }

        private com.google.protobuf.MapField<String, String> internalGetMutableExtendInfo() {
            onChanged();
            ;
            if (extendInfo_ == null) {
                extendInfo_ = com.google.protobuf.MapField.newMapField(ExtendInfoDefaultEntryHolder.defaultEntry);
            }
            if (!extendInfo_.isMutable()) {
                extendInfo_ = extendInfo_.copy();
            }
            return extendInfo_;
        }

        public int getExtendInfoCount() {
            return internalGetExtendInfo().getMap().size();
        }

        /**
         * <code>map&lt;string, string&gt; extendInfo = 3;</code>
         */

        public boolean containsExtendInfo(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            return internalGetExtendInfo().getMap().containsKey(key);
        }

        /**
         * Use {@link #getExtendInfoMap()} instead.
         */
        @Deprecated
        public java.util.Map<String, String> getExtendInfo() {
            return getExtendInfoMap();
        }

        /**
         * <code>map&lt;string, string&gt; extendInfo = 3;</code>
         */

        public java.util.Map<String, String> getExtendInfoMap() {
            return internalGetExtendInfo().getMap();
        }

        /**
         * <code>map&lt;string, string&gt; extendInfo = 3;</code>
         */

        public String getExtendInfoOrDefault(String key, String defaultValue) {
            if (key == null) {
                throw new NullPointerException();
            }
            java.util.Map<String, String> map = internalGetExtendInfo().getMap();
            return map.containsKey(key) ? map.get(key) : defaultValue;
        }

        /**
         * <code>map&lt;string, string&gt; extendInfo = 3;</code>
         */

        public String getExtendInfoOrThrow(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            java.util.Map<String, String> map = internalGetExtendInfo().getMap();
            if (!map.containsKey(key)) {
                throw new IllegalArgumentException();
            }
            return map.get(key);
        }

        public Builder clearExtendInfo() {
            internalGetMutableExtendInfo().getMutableMap().clear();
            return this;
        }

        /**
         * <code>map&lt;string, string&gt; extendInfo = 3;</code>
         */

        public Builder removeExtendInfo(String key) {
            if (key == null) {
                throw new NullPointerException();
            }
            internalGetMutableExtendInfo().getMutableMap().remove(key);
            return this;
        }

        /**
         * Use alternate mutation accessors instead.
         */
        @Deprecated
        public java.util.Map<String, String> getMutableExtendInfo() {
            return internalGetMutableExtendInfo().getMutableMap();
        }

        /**
         * <code>map&lt;string, string&gt; extendInfo = 3;</code>
         */
        public Builder putExtendInfo(String key, String value) {
            if (key == null) {
                throw new NullPointerException();
            }
            if (value == null) {
                throw new NullPointerException();
            }
            internalGetMutableExtendInfo().getMutableMap().put(key, value);
            return this;
        }

        /**
         * <code>map&lt;string, string&gt; extendInfo = 3;</code>
         */

        public Builder putAllExtendInfo(java.util.Map<String, String> values) {
            internalGetMutableExtendInfo().getMutableMap().putAll(values);
            return this;
        }

        @Override
        public final Builder setUnknownFields(final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.setUnknownFields(unknownFields);
        }

        @Override
        public final Builder mergeUnknownFields(final com.google.protobuf.UnknownFieldSet unknownFields) {
            return super.mergeUnknownFields(unknownFields);
        }

        // @@protoc_insertion_point(builder_scope:GetRequest)
    }

}
