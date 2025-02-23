package com.hezy.service;

import com.hezy.mapper.UserMapper;
import com.hezy.pojo.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Log4j2
public class DelayService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PlatformTransactionManager transactionManager;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * 延迟执行
     * @param time
     */
    public void delay1(Integer time) {
        scheduler.schedule(() -> {
            log.info("run delay1...date={} time={}",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), time);
        }, time, TimeUnit.SECONDS);
    }

    /**
     * 声明式事务
     */
    @Transactional(rollbackFor = Exception.class)
    public void delay2(User user, Integer time) {
        scheduler.schedule(() -> {
            log.info("run delay2...date={} time={}",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), time);
            userMapper.insert(user);

            int i = 1 / 0;

            user.setId(3);
            userMapper.insert(user);
        }, time, TimeUnit.SECONDS);
    }

    /**
     * 编程式事务
     */
    public void delay3(User user, Integer time) {
        scheduler.schedule(() -> {
            TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
            try {
                log.info("run delay3...date={} time={}",
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), time);
                userMapper.insert(user);

                // 故意产生异常
                int i = 1 / 0;

                user.setId(3);
                userMapper.insert(user);

                // 提交事务
                transactionManager.commit(status);
            } catch (Exception e) {
                transactionManager.rollback(status);
                e.printStackTrace();
            }
        }, time, TimeUnit.SECONDS);
    }
}
