package ecjtu.zjf.takeoutapi.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import ecjtu.zjf.takeoutapi.entity.Saler;
import ecjtu.zjf.takeoutapi.service.ISalerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 商户信息 前端控制器
 * </p>
 *
 * @author zjf
 * @since 2019-04-21
 */
@RestController
@RequestMapping("/saler")
@Api("商户相关api")
public class SalerController {

    @Autowired
    ISalerService iSalerService;


    @ApiOperation("商户详细信息")
    @ApiImplicitParam(paramType="query",name="id",dataType="int",required=true,value="目标商户id",defaultValue="")
    @PostMapping(value="/detail")
    public String detail(@RequestParam int id){
        Saler res=iSalerService.getById(id);
        return JSON.toJSONString(res);
    }

    @ApiOperation("得到现在用户")
    @PostMapping(value="/nowUser")
    public String nowUser(){
        Saler user = (Saler) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        return JSON.toJSONString(user);
    }
}
