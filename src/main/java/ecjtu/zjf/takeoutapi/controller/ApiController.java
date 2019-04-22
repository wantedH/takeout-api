package ecjtu.zjf.takeoutapi.controller;

import com.alibaba.fastjson.JSON;
import ecjtu.zjf.takeoutapi.entity.Saler;
import io.swagger.annotations.Api;


import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api("功能api")
public class ApiController {
    @ApiOperation("得到现在用户")
    @PostMapping(value="/nowUser")
    public String nowUser(){
//        Saler user = (Saler) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        return JSON.toJSONString(SecurityContextHolder.getContext().getAuthentication() .getPrincipal());
    }
}
