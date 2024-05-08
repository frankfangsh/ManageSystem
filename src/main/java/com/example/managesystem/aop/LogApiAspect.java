package com.example.managesystem.aop;

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONUtil;
import com.example.managesystem.entity.BaseUser;
import com.example.managesystem.utils.TLocalHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@Component
@Aspect
@Order(1)
public class LogApiAspect {

    @Around(value = "execution(* com.example.managesystem..*.*(..))")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        try {
            String token = request.getHeader("token");
            if (!StringUtils.isEmpty(token)) {
                BaseUser user = JSONUtil.toBean(Base64.decodeStr(token), BaseUser.class);
                if (Objects.nonNull(user)) {
                    TLocalHelper.setUserInfo(user);
                }
            } else {
                TLocalHelper.setUserInfo(new BaseUser(-1L, "-1", "-1"));
            }
        } catch (Exception e) {
            TLocalHelper.setUserInfo(new BaseUser(-1L, "-1", "-1"));
        }
        Object result = joinPoint.proceed();
        return result;
    }

}

