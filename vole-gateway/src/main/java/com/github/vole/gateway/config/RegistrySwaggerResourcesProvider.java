package com.github.vole.gateway.config;

import com.github.vole.gateway.constants.ServiceNameConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问http://localhost:9999/swagger-resources获取需要集成在线文档api业务模块的swagger地址
 * 例如:[/demo/v2/api-docs,/mps/v2/api-docs]
 *      集成地址:http://localhost:9999/demo/v2/api-docs
 * 集成easymock 聚合在线文档
 * routeLocator 聚合swagger
 */
@Component
@Primary
public class RegistrySwaggerResourcesProvider implements SwaggerResourcesProvider {
    private final RouteLocator routeLocator;

    public RegistrySwaggerResourcesProvider(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();

        List<Route> routes = routeLocator.getRoutes();
        routes.forEach(route -> {
            //授权不维护到swagger
            if (!StringUtils.contains(route.getId(), ServiceNameConstant.AUTH_SERVICE)){
                resources.add(swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs")));
            }
        });

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}