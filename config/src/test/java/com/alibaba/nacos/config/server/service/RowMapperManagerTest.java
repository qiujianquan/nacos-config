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

package com.fantasy.nacos.config.server.service;

import com.fantasy.nacos.config.server.model.User;
import com.fantasy.nacos.config.server.service.repository.RowMapperManager;
import com.fantasy.nacos.core.utils.ClassUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.core.RowMapper;

public class RowMapperManagerTest {

    @Test
    public void testUserMapper() {
        RowMapper<User> mapper = new RowMapperManager.UserRowMapper();
        Assert.assertEquals(ClassUtils.resolveGenericTypeByInterface(mapper.getClass()).getSimpleName(),
                User.class.getSimpleName());
    }

}
