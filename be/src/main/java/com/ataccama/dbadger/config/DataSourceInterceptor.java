package com.ataccama.dbadger.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DataSourceInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final var sessionId = request.getSession().getId();
        //routing.setTargetDataSources(new ConcurrentHashMap<>());

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
