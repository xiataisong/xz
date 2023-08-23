package com.xz.dao;

import com.github.pagehelper.Page;
import com.xz.pojo.CheckItem;

import java.util.List;

public interface CheckItemDao {
    void add(CheckItem checkItem);

    Page<CheckItem> findByCondition(String queryString);

    /**
     * 通过检查项id查询检查组检查项关系，统计个数
     * @param id
     * @return
     */
    int findCountByCheckItemId(int id);

    /**
     * 删除检查项
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
