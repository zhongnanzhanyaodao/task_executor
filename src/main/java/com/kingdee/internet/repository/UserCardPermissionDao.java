package com.kingdee.internet.repository;

import com.kingdee.internet.entity.UserCardPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户-银行卡权限绑定关系Dao
 */
public interface UserCardPermissionDao {
    /**
     * 插入一条用户-卡绑定关系
     * @param userCardPermission
     * @return
     */
    int insert(UserCardPermission userCardPermission);

    /**
     * 删除用户指定卡的权限
     * @param userCardPermission
     * @return
     */
	int delUserCardPermission(UserCardPermission userCardPermission);

    /**
     * 查找用户被授权卡权限
     * @param userCardPermission
     * @return
     */
    List<UserCardPermission> findAuthorisedCardPermission(UserCardPermission userCardPermission);

    /**
     * 如果指定权限赋予人下无指定用户的卡权限，则删除所有对应的所有Permissions
     * @param createBy 权限赋予人
     * @param userId 权限所有者
     */
    int delAllPermissionsIfNotExists(@Param("createBy") String createBy, @Param("userId") String userId);

    /**
     * 如果指定权限赋予人下无指定用户的卡权限，则删除所有对应的所有UserPerEnterprise
     * @param createBy 权限赋予人
     * @param userId 权限所有者
     */
    int delAllUserPerEnterpriseIfNotExists(@Param("createBy") String createBy, @Param("userId") String userId);
}
