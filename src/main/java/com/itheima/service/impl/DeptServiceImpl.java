package com.itheima.service.impl;

import com.itheima.aop.MyLog;
import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.transaction.annotation.Propagation.SUPPORTS;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @MyLog
    @Override
    public List<Dept> list() {
        List<Dept> list = deptMapper.list();
        return list;
    }
    @MyLog
    @Transactional(rollbackFor = Exception.class,propagation =SUPPORTS)
    @Override
    public void delete(Integer id) {
        try {
            deptMapper.deleteById(id);  //根据ID删除部门数据

            int i = 1/0;


            empMapper.deleteByDeptId(id);   //根据部门的id删除部门下的员工
        } finally {
            //记录日记
        }
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        deptMapper.insert(dept);
    }
}
