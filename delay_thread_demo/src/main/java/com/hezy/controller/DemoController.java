package com.hezy.controller;

import com.hezy.pojo.User;
import com.hezy.service.DelayService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/demo")
@Log4j2
public class DemoController {

    @Autowired
    private DelayService delayService;

    @GetMapping
    public String demo() {
        return "Hello World!";
    }

    @GetMapping("/delay1/{time}")
    public String delay1(@PathVariable Integer time) {
        log.info("enter delay1...date={} time={}",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), time);
        delayService.delay1(time);
        return "success";
    }

    @PostMapping("/delay2/{time}")
    public String delay2(@RequestBody User user, @PathVariable Integer time) {
        log.info("enter delay2...date={} time={}",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), time);
        delayService.delay2(user, time);
        return "success";
    }

    @PostMapping("/delay3/{time}")
    public String delay3(@RequestBody User user, @PathVariable Integer time) {
        log.info("enter delay3...date={} time={}",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), time);
        delayService.delay3(user, time);
        return "success";
    }
}
