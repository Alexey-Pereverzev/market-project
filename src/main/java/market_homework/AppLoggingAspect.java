package market_homework;

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

    private long cartTime;
    private long categoryTime;
    private long productTime;

    @PostConstruct
    private void init(){
        cartTime=0;
        categoryTime=0;
        productTime=0;
    }

    @Around("execution(public * market_homework.services.CartService.*(..))")
    public Object cartProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        cartTime = cartTime + end - begin;
        return out;
    }

    @Around("execution(public * market_homework.services.CategoryService.*(..))")
    public Object categoryProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        categoryTime = categoryTime + end - begin;
        return out;
    }

    @Around("execution(public * market_homework.services.ProductService.*(..))")
    public Object productProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        productTime = productTime + end - begin;
        return out;
    }

    public List<String> returnStatistics() {
        return new ArrayList<>(Arrays.asList("ProductService: " + productTime + "ms",
                "CategoryService: " + categoryTime + "ms",
                "CartService: " + cartTime + "ms"));
    }
}
