package xyz.dowenliu.bug;

import jakarta.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RunBiz implements CommandLineRunner {
    @Resource
    private Business business;

    @Override
    public void run(String... args) {
        // This will trigger the fallback method: handleFallback(int, Throwable)
        business.doSomething(0);
        // This should trigger the fallback method: handleFallback(String, Throwable)
        // But it will fall into a bug, and throw a reflection exception
        business.doSomethingElse(null);
    }
}
