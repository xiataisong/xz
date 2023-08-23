package com.xz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xz.dao.CheckGroupDao;
import com.xz.entity.PageResult;
import com.xz.entity.QueryPageBean;
import com.xz.pojo.CheckGroup;
import com.xz.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 *
 * </p>
 *
 * @author: Eric
 * @since: 2021/2/20
 */
@Service
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    /**
     * 添加检查组
     *
     * @param checkGroup
     * @param checkitemIds
     */
    @Override
    @Transactional
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        //- 添加检查组信息
        checkGroupDao.add(checkGroup);
        //- 获取新增的检查组id
        Integer id = checkGroup.getId();
        //- 遍历选中的检查项id数组，空判断
        if(null != checkitemIds) {
            //- 添加检查组与检查项的关系
            for (Integer checkitemId : checkitemIds) {
                checkGroupDao.addCheckGroupCheckItem(id,checkitemId);
            }
        }
        //- 事务控制
    }
    /**
     * 分页条件查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
        //- 判断是否有查询条件，如果有则实现模糊查询，拼接%
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        //- 使用PageHelper.startPage(页码，大小)
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //- 调用dao的findByCondition条件查询，返回page对象
        Page<CheckGroup> page = checkGroupDao.findByCondition(queryPageBean.getQueryString());
        //- 通过page对象获取total,result分页结果集
        //- 封装到pageResult，返回给controller
        PageResult<CheckGroup> pageResult = new PageResult<CheckGroup>(page.getTotal(),page.getResult());
        return pageResult;
    }
}
