package com.xz.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xz.dao.CheckItemDao;
import com.xz.entity.PageResult;
import com.xz.entity.QueryPageBean;
import com.xz.exception.MyException;
import com.xz.pojo.CheckItem;
import com.xz.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;
    @Override
    public void add(CheckItem checkItem) {
       checkItemDao.add(checkItem);
    }

    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {
        // 判断是否有条件，有则要使用模糊查询,【注意取反 ！】
        if(!StringUtils.isEmpty(queryPageBean.getQueryString())){
            // 有查询条件, 拼接%
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        //第二种，Mapper接口方式的调用，推荐这种使用方式。 ThreadLocale
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        // Page<E> extends ArrayList<E> list
        Page<CheckItem> page = checkItemDao.findByCondition(queryPageBean.getQueryString());
        // 获取总记录数
        long total = page.getTotal();
        List<CheckItem> checkItems = page.getResult();
        return new PageResult<CheckItem>(total,checkItems);
    }
    /**
     * 删除
     * @param id
     */
    @Override
    public void deleteById(int id) {
        //- 判断检查项是否被检查组使用了，通过检查项id查询检查组检查项关系，统计个数
        int count = checkItemDao.findCountByCheckItemId(id);
        //- 如果个数>0，
        if(count > 0){
            //被使用了，报错(MyException, 终止不符合业务代码的执行) 不能删除
            throw new MyException("该检查项被检查组使用了，不能删除");
        }
        //- 如果个数=0，删除检查项
        checkItemDao.deleteById(id);
    }

    /**
     * 通过id查询检查项
     * @param id
     * @return
     */
    @Override
    public CheckItem findById(int id) {
        return checkItemDao.findById(id);
    }

    /**
     * 更新检查项
     * @param checkitem
     */
    @Override
    public void update(CheckItem checkitem) {
        checkItemDao.update(checkitem);
    }

    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }
}
