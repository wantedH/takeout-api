package ecjtu.zjf.takeoutapi.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ecjtu.zjf.takeoutapi.dto.OrderGoodsDTO;
import ecjtu.zjf.takeoutapi.entity.Goods;
import ecjtu.zjf.takeoutapi.service.IGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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

}
