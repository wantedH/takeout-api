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
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.sql.SQLIntegrityConstraintViolationException;
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


    @ApiOperation("商户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="username",dataType="String",required=true,value="用户的姓名",defaultValue=""),
            @ApiImplicitParam(paramType="query",name="password",dataType="String",required=true,value="用户的密码",defaultValue="")
    })
    @PostMapping("/login")
    public boolean login(@RequestParam String username,@RequestParam String password){
        QueryWrapper<Saler> salerQueryWrapper=new QueryWrapper<>();
        Map<String,Object> data=new HashMap<>();

        data.put("username",username);
        data.put("password",password);
        salerQueryWrapper.allEq(data);

        int count=iSalerService.count(salerQueryWrapper);
        if(count>0) return true;
        return false;
    }
    @ApiOperation("商户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", dataType = "String", required = true, value = "商户账号", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "password", dataType = "String", required = true, value = "商户密码", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "name", dataType = "String", required = true, value = "店铺名称", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "location", dataType = "String", required = true, value = "店铺地址", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "tel", dataType = "int", required = true, value = "商户联系电话", defaultValue = "")
    })
    @PostMapping("/register")
    public boolean register(@ModelAttribute Saler saler){
        boolean res=iSalerService.save(saler);
        return res;
    }


}
