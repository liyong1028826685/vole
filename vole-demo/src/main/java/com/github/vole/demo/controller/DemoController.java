package com.github.vole.demo.controller;

import com.github.vole.demo.fegin.TraceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "核算统计", tags = "核算统计")
@RestController
public class DemoController {

    @Autowired
    TraceService traceService;

    @ApiOperation("trace")
    @GetMapping("/trace/{name}")
    public String demoTrace(@PathVariable String name) {

        return traceService.trace(name);
    }
}
