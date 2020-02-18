package com.chengxiaoxiao.model.repository;

import com.chengxiaoxiao.model.web.pojos.SysRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 角色实体类
 *
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2020/2/15 10:04 下午
 * @Description:
 */
public interface SysRoleRepository extends BaseDao<SysRole, String> {

    @Query(value = "select sys_role.id,sys_role.`name`,sys_role.descript from sys_role INNER JOIN sys_user_role on sys_role.id = sys_user_role.role_id where user_id=:id and delete_status=0",nativeQuery = true)
    List<Object[]> findRoleListByUserId(@Param("id") String id);
}
