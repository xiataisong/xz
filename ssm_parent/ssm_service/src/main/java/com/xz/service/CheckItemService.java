package com.xz.service;

import com.xz.entity.PageResult;
import com.xz.entity.QueryPageBean;
import com.xz.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {
    void add(CheckItem checkItem);

    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);
    /**
     * 删除
     * @param id
     */
    void deleteById(int id);
    /**
     * 通过id查询检查项
     * @param id
     * @return
     */
    CheckItem findById(int id);

    /**
     * 更新检查项
     * @param checkitem
     */
    void update(CheckItem checkitem);

    List<CheckItem> findAll();
}
