package org.example.november_market_2.core;

import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@Data
public class AppLoggingAspect {

    private long userTime;
    private long productTime;

    private long orderTime;

    @PostConstruct
    private void init(){
        orderTime=0;
        userTime=0;
        productTime=0;
    }

    @Around("execution(public * org.example.november_market_2.core.services.OrderService.*(..))")
    public Object categoryProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        orderTime = orderTime + end - begin;
        return out;
    }

    @Around("execution(public * org.example.november_market_2.core.services.ProductService.*(..))")
    public Object productProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        productTime = productTime + end - begin;
        return out;
    }

    @Around("execution(public * org.example.november_market_2.core.services.UserService.*(..))")
    public Object userProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        userTime = userTime + end - begin;
        return out;
    }

    public List<String> returnStatistics () {
        return new ArrayList<>(Arrays.asList("ProductService: " + productTime + "ms",
                "OrderService: " + orderTime + "ms", "UserService: " + userTime + "ms"));
    }
}
