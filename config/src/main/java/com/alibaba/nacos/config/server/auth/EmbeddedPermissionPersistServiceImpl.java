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

package com.fantasy.nacos.config.server.auth;

import com.fantasy.nacos.config.server.configuration.ConditionOnEmbeddedStorage;
import com.fantasy.nacos.config.server.model.Page;
import com.fantasy.nacos.config.server.service.repository.embedded.EmbeddedStoragePersistServiceImpl;
import com.fantasy.nacos.config.server.service.repository.PaginationHelper;
import com.fantasy.nacos.config.server.service.repository.embedded.DatabaseOperate;
import com.fantasy.nacos.config.server.service.sql.EmbeddedStorageContextUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.fantasy.nacos.config.server.service.repository.RowMapperManager.PERMISSION_ROW_MAPPER;

/**
 * There is no self-augmented primary key.
 *
 * @author <a href="mailto:liaochuntao@live.com">liaochuntao</a>
 */
@Conditional(value = ConditionOnEmbeddedStorage.class)
@Component
public class EmbeddedPermissionPersistServiceImpl implements PermissionPersistService {

    @Autowired
    private DatabaseOperate databaseOperate;

    @Autowired
    private EmbeddedStoragePersistServiceImpl persistService;

    public Page<PermissionInfo> getPermissions(String role, int pageNo, int pageSize) {
        PaginationHelper<PermissionInfo> helper = persistService.createPaginationHelper();

        String sqlCountRows = "select count(*) from permissions where ";
        String sqlFetchRows = "select role,resource,action from permissions where ";

        String where = " role='" + role + "' ";

        if (StringUtils.isBlank(role)) {
            where = " 1=1 ";
        }

        Page<PermissionInfo> pageInfo = helper
                .fetchPage(sqlCountRows + where, sqlFetchRows + where, new ArrayList<String>().toArray(), pageNo,
                        pageSize, PERMISSION_ROW_MAPPER);

        if (pageInfo == null) {
            pageInfo = new Page<>();
            pageInfo.setTotalCount(0);
            pageInfo.setPageItems(new ArrayList<>());
        }
        return pageInfo;
    }

    /**
     * Execute ddd user permission operation.
     *
     * @param role role info string value.
     * @param resource resource info string value.
     * @param action action info string value.
     */
    public void addPermission(String role, String resource, String action) {
        String sql = "INSERT into permissions (role, resource, action) VALUES (?, ?, ?)";
        EmbeddedStorageContextUtils.addSqlContext(sql, role, resource, action);
        databaseOperate.blockUpdate();
    }

    /**
     * Execute delete user permission operation.
     *
     * @param role role info string value.
     * @param resource resource info string value.
     * @param action action info string value.
     */
    public void deletePermission(String role, String resource, String action) {
        String sql = "DELETE from permissions WHERE role=? and resource=? and action=?";
        EmbeddedStorageContextUtils.addSqlContext(sql, role, resource, action);
        databaseOperate.blockUpdate();
    }

}