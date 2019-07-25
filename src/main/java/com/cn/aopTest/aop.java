package com.cn.aopTest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;

@Component
@Aspect
public class aop {
    // �൱�ڴ���һ��Log����������Ҫ��new����
    Logger logger = LoggerFactory.getLogger(aop.class);
    // ��������� service �����addUser����
    @Pointcut("execution(public * com.cn.service..*.addUser(..))")
    public void addLog(){ }

    // ���������service�����delete����
    @Pointcut("execution(public * com.cn.service..*.delete(..))")
    public void delete(){ }

    @Before("addLog()||delete()")
    public void doBefore(JoinPoint joinPoint){
        logger.info("ģ��Ŀ�귽��֮ǰ");
        logger.info("Ŀ�귽��Ϊ��"+ joinPoint.getSignature().getDeclaringTypeName()+""
                + joinPoint.getSignature().getName());
        logger.info("����Ϊ��"+Arrays.toString(joinPoint.getArgs()));
        logger.info("��֯��Ķ���"+joinPoint.getTarget());
    }

    @AfterReturning(returning = "returnValue",pointcut = "addLog()||delete()")
    public void doAfterReturning(JoinPoint joinPoint , Object returnValue){
        logger.info("ģ����־��¼........");
        logger.info("Ŀ�귽��Ϊ��"+joinPoint.getSignature().getDeclaringTypeName()+" "
                +joinPoint.getSignature().getName());
        logger.info("����Ϊ��"+Arrays.toString(joinPoint.getArgs()));

    }
}
