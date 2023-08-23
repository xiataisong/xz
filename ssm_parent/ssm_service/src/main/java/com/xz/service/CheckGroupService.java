package com.xz.service;

import com.xz.entity.PageResult;
import com.xz.entity.QueryPageBean;
import com.xz.pojo.CheckGroup;

public interface CheckGroupService {
    /**
     * 添加检查组
     * @param checkGroup
     * @param checkitemIds
     */
    void add(CheckGroup checkGroup, Integer[] checkitemIds);
    /**
     * 分页条件查询
     * @param queryPageBean
     * @return
     */
    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);
}
