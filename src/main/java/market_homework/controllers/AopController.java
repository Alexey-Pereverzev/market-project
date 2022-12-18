package market_homework.controllers;

import lombok.RequiredArgsConstructor;
import market_homework.AppLoggingAspect;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/statistic")
public class AopController {
    private final AppLoggingAspect appLoggingAspect;

    @GetMapping
    public List<String> getStatistics() {
        return appLoggingAspect.returnStatistics();
    }
}
