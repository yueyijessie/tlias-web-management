package com.jessie.service.impl;

import com.jessie.mapper.DeptLogMapper;
import com.jessie.pojo.DeptLog;
import com.jessie.service.DeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptLogServiceImpl implements DeptLogService {

    @Autowired
    private DeptLogMapper deptLogMapper;

    // @Transactional(propagation = Propagation.REQUIRED) 是默认值，当前有事务则加入，没有则创建（加入当前事务，如果失败，则一起回滚）
    // @Transactional(propagation = Propagation.REQUIRES_NEW) 总是创建新事务，当前有事务则挂起当前事务，然后创建新事务，执行完毕后继续执行未完成的事务（无论前事务是否成功，都不影响新事务执行）
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insert(DeptLog deptLog) {
        deptLogMapper.insert(deptLog);
    }
}
