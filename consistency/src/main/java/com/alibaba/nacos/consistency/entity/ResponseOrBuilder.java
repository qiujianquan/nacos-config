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

@SuppressWarnings("all")
public interface ResponseOrBuilder extends
        // @@protoc_insertion_point(interface_extends:Response)
        com.google.protobuf.MessageOrBuilder {

    /**
     * <code>bytes data = 1;</code>
     */
    com.google.protobuf.ByteString getData();

    /**
     * <code>string errMsg = 2;</code>
     */
    String getErrMsg();

    /**
     * <code>string errMsg = 2;</code>
     */
    com.google.protobuf.ByteString getErrMsgBytes();

    /**
     * <code>bool success = 3;</code>
     */
    boolean getSuccess();
}