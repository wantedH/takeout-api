package ecjtu.zjf.takeoutapi.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ecjtu.zjf.takeoutapi.common.FileUtil;
import ecjtu.zjf.takeoutapi.dto.OrderGoodsDTO;
import ecjtu.zjf.takeoutapi.entity.Goods;
import ecjtu.zjf.takeoutapi.entity.Saler;
import ecjtu.zjf.takeoutapi.service.IGoodsService;
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
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 菜品 前端控制器
 * </p>
 *
 * @author zjf
 * @since 2019-04-21
 */
@RestController
@RequestMapping("/goods")
@Api("商品相关接口")
public class GoodsController {

    @Autowired
    IGoodsService goodsService;

    @ApiOperation("商户所有商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query",name="id",dataType="int",required=true,value="目标商户id",defaultValue=""),
            @ApiImplicitParam(paramType = "query", name = "nowPage", dataType = "int", required = false, value = "现在所在页数", defaultValue = "1")
    })
    @PostMapping("/saleGoods")
    public String getSaleGoods(@RequestParam Integer id,@RequestParam(required = false,defaultValue = "1") Integer nowPage){

        List<Goods> res=goodsService.pageSaleGoods(id,nowPage);
        return JSON.toJSONString(res);
    }

    @ApiOperation("订单商品详情列表")
    @ApiImplicitParam(paramType = "query", name = "id", dataType = "String", required = true, value = "目标订单id", defaultValue = "")
    @PostMapping("/orderGoods")
    public String getOrderGoods(@RequestParam String id){
        List<OrderGoodsDTO> res=goodsService.listFromOrder(id);
        return JSON.toJSONString(res);
    }

    @ApiOperation("商品详情")
    @ApiImplicitParam(paramType = "query", name = "id", dataType = "String", required = true, value = "目标商品id", defaultValue = "")
    @PostMapping("/detail")
    public String detail(@RequestParam  Integer id){
        return JSON.toJSONString(goodsService.getById(id));
    }


    @ApiOperation("移除商品")
    @ApiImplicitParam(paramType = "query", name = "id", dataType = "String", required = true, value = "目标订单id", defaultValue = "")
    @PostMapping(value = "/delete")
    public boolean delete(@RequestParam int id){
        return goodsService.removeById(id);
    }
    @ApiOperation("新增商品")
    @PostMapping(value = "/add")
    public boolean add(@RequestParam("file") MultipartFile file,
                       @RequestParam String name,
                       @RequestParam Double price,
                       @RequestParam String doc) throws IOException {
        Goods goods=new Goods();
        String[] words=file.getOriginalFilename().split("\\.");
        String filename=new Date().getTime()+"."+words[words.length-1];
        Saler saler = (Saler) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();

        fileUtil.transformFile(file,filename);

        goods.setAvatar("pic/"+filename+'?'+ UUID.randomUUID());
        goods.setSalerId(saler.getId());
        goods.setState(true);
        goods.setName(name);
        goods.setPrice(price);
        goods.setDoc(doc);

        return goodsService.save(goods);
    }

    @ApiOperation("修改信息")
    @PostMapping(value = "/change")
    public boolean change( Goods goods){
        Saler tar = (Saler) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        if(tar.getId()!=goods.getSalerId()) return false;
        QueryWrapper<Goods> wrapper=new QueryWrapper<>();
        wrapper.eq("id",goods.getId());
        return goodsService.update(goods,wrapper);
    }

    @Autowired FileUtil fileUtil;

    @ApiOperation("修改展示图")
    @PostMapping(value = "/uploadAvatar")
    public boolean uploadAvatar(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id) throws IOException {
        String[] words=file.getOriginalFilename().split("\\.");
        String name=new Date().getTime()+"."+words[words.length-1];

        fileUtil.transformFile(file,name);

        Goods goods=new Goods();

        goods.setAvatar("pic/"+name+'?'+ UUID.randomUUID());

        QueryWrapper<Goods> wrapper=new QueryWrapper<>();
        wrapper.eq("id",id);
        boolean res = goodsService.update(goods,wrapper);
        return res;
    }



}
