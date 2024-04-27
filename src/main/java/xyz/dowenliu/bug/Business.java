package xyz.dowenliu.bug;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Component;

@Component
public class Business {
    @SentinelResource(value = "doSomething", fallback = "handleFallback")
    public void doSomething(int a) {
        if (a <= 0) {
            throw new IllegalArgumentException("a must be positive");
        }
        System.out.println("a = " + a);
    }

    public void handleFallback(int a, Throwable e) {
        System.out.println("fallback: a = " + a + ", e = " + e);
    }

    // Maybe this is a long class and after a lot of code, we have another method

    @SentinelResource(value = "doSomethingElse", fallback = "handleFallback")
    public void doSomethingElse(String a) {
        if (a == null) {
            throw new IllegalArgumentException("a must not be null");
        }
        System.out.println("a = " + a);
    }

    public void handleFallback(String a, Throwable e) {
        System.out.println("fallback: a = " + a + ", e = " + e);
    }
}
