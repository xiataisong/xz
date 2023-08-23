package com.xz.dao;

import com.github.pagehelper.Page;
import com.xz.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *
 * </p>
 *
 * @author: Eric
 * @since: 2021/2/20
 */
public interface CheckGroupDao {
    /**
     * 添加检查组信息
     * @param checkGroup
     */
    void add(CheckGroup checkGroup);

    /**
     * 添加检查组与检查项的关系 参数类型相同时，要取别名
     * 也可以封装到map里
     * @param checkGroupId
     * @param checkitemId
     */
    void addCheckGroupCheckItem(@Param("checkGroupId") Integer checkGroupId, @Param("checkitemId") Integer checkitemId);
    //分页查询
    Page<CheckGroup> findByCondition(String queryString);
}
