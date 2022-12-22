package org.example.november_market_2.core.controllers;

import lombok.RequiredArgsConstructor;
import org.example.november_market_2.core.AppLoggingAspect;
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

