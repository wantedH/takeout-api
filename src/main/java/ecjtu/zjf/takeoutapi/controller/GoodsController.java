package ecjtu.zjf.takeoutapi.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ecjtu.zjf.takeoutapi.entity.Goods;
import ecjtu.zjf.takeoutapi.service.IGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @ApiImplicitParam(paramType="query",name="id",dataType="int",required=true,value="目标商户id",defaultValue="")
    @PostMapping("/saleGoods")
    public String getSaleGoods(@RequestParam Integer id){
        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("saler_id",id);
        List<Goods> res=goodsService.list(queryWrapper);
        return JSON.toJSONString(res);
    }

    @ApiOperation("订单商品详情列表")
    @ApiImplicitParam(paramType="query",name="id",dataType="int",required=true,value="目标订单id",defaultValue="")
    @PostMapping("/orderGoods")
    public String getOrderGoods(@RequestParam Integer id){
        List<Goods> res=goodsService.listFromOrder(id);
        return JSON.toJSONString(res);
    }
}
