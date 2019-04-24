package ecjtu.zjf.takeoutapi.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
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
import springfox.documentation.annotations.Cacheable;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


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

    @ApiOperation("商户列表")
    @ApiImplicitParam(paramType = "query", name = "nowPage", dataType = "int", required = false, value = "现在所在页数", defaultValue = "1")
    @PostMapping("/list")
    public String list(@RequestParam(defaultValue = "1",required = false) int nowPage){
        return JSON.toJSONString(iSalerService.pageSaleGoods(nowPage));
    }

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
    public boolean change(Saler saler){
        QueryWrapper<Saler> wrapper=new QueryWrapper<>();
        Saler tar = (Saler) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        wrapper.eq("id",tar.getId());

        boolean res=iSalerService.update(saler,wrapper);
        if(res){
            if(StringUtils.isNotEmpty(saler.getName())) tar.setName(saler.getName());
            if(StringUtils.isNotEmpty(saler.getLocation())) tar.setLocation(saler.getLocation());
            if(StringUtils.isNotEmpty(saler.getTel())) tar.setTel(saler.getTel());
        }
        return  res;
    }

    @Autowired FileUtil fileUtil;

    @ApiOperation("修改展示图")
    @PostMapping(value = "/uploadAvatar")
    public boolean uploadAvatar(@RequestParam MultipartFile file) throws IOException {
        String[] words=file.getOriginalFilename().split("\\.");
        String name=new Date().getTime()+"."+words[words.length-1];

        fileUtil.transformFile(file,name);

        Saler saler = (Saler) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        String oldName=saler.getAvatar().split("\\?")[0];
        boolean delFlag=true;
        if(oldName.length()==saler.getAvatar().length()) delFlag=false;
        saler.setAvatar("pic/"+name+'?'+ UUID.randomUUID());

        QueryWrapper<Saler> wrapper=new QueryWrapper<>();
        wrapper.eq("id",saler.getId());
        boolean res= iSalerService.update(saler,wrapper);
        if(res&&delFlag){
            fileUtil.deletePicFile(oldName.substring(4));
        }
        return res;
    }

}
