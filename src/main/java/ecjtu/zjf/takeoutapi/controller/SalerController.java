package ecjtu.zjf.takeoutapi.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import ecjtu.zjf.takeoutapi.common.FileUtil;
import ecjtu.zjf.takeoutapi.entity.Saler;
import ecjtu.zjf.takeoutapi.service.ISalerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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


    @ApiOperation("商户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", dataType = "String", required = true, value = "商户账号", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "password", dataType = "String", required = true, value = "商户密码", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "name", dataType = "String", required = true, value = "店铺名称", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "location", dataType = "String", required = true, value = "店铺地址", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "tel", dataType = "String", required = true, value = "商户联系电话", defaultValue = "")
    })
    @PostMapping(value="/register")
    public boolean register(@ModelAttribute Saler saler){
        boolean res=iSalerService.save(saler);
        return res;
    }
    @ApiOperation("验证账号是否存在")
    @ApiImplicitParam(paramType="query",name="username",dataType="String",required=true,value="目标商户id",defaultValue="")
    @PostMapping(value = "/check_username")
    public boolean check_username(@RequestParam String username){
        QueryWrapper<Saler> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",username);
        int res=iSalerService.count(queryWrapper);
        if(res>0) return true;
        return false;
    }

    @ApiOperation("修改信息")
    @PostMapping(value = "/change")
    public boolean change(@RequestBody Saler saler){
        QueryWrapper<Saler> wrapper=new QueryWrapper<>();
        wrapper.eq("id",saler.getId());
        return iSalerService.update(saler,wrapper);
    }

    @ApiOperation("修改展示图")
    @PostMapping(value = "/uploadAvatar")
    @Async
    public boolean uploadAvatar(@RequestParam MultipartFile file) throws IOException {
        String name=FileUtil.TransformFile(file);
        Saler saler = (Saler) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        saler.setAvatar(name);

        QueryWrapper<Saler> wrapper=new QueryWrapper<>();
        wrapper.eq("id",saler.getId());
        return iSalerService.update(saler,wrapper);
    }

}
