package ecjtu.zjf.takeoutapi.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ecjtu.zjf.takeoutapi.common.JsonTools;
import ecjtu.zjf.takeoutapi.dto.GoodsOrderDTO;
import ecjtu.zjf.takeoutapi.dto.OrderGoodsDTO;
import ecjtu.zjf.takeoutapi.dto.OrdersAddDTO;
import ecjtu.zjf.takeoutapi.entity.Orders;
import ecjtu.zjf.takeoutapi.service.IOrderService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author zjf
 * @since 2019-04-21
 */
@RestController
@RequestMapping("/order")
@Api("订单相关接口")
public class OrderController {

    @Autowired
    IOrderService orderService;

    @ApiOperation("用户订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "用户唯一认证", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "nowPage", dataType = "int", required = false, value = "现在所在页数", defaultValue = "1")
    })
    @PostMapping("/pageList")
    public String pageList(@RequestParam String token,@RequestParam(required = false,defaultValue = "1") int nowPage){
        return JSON.toJSONString(orderService.pageSaleGoods(token,nowPage));
    }

    @ApiOperation("商户订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "salerId", dataType = "int", required = true, value = "对应商户id", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "nowPage", dataType = "int", required = false, value = "现在所在页数", defaultValue = "1")
    })
    @PostMapping("/salerOrders")
    public String salerOrders(@RequestParam Integer salerId,@RequestParam(required = false,defaultValue = "1") int nowPage){
        return JSON.toJSONString(orderService.pageSalerOrders(salerId,nowPage));
    }

    @ApiOperation("订单生成")
    @PostMapping("/addOrder")
    public String addOrder( @RequestBody OrdersAddDTO ordersAddDTO){
//            ,  @RequestBody List<GoodsOrderDTO> goodsOrderDTOS){
        System.out.println(ordersAddDTO);
        Orders orders = JsonTools.convert(ordersAddDTO,Orders.class);

        orders.setId(UUID.randomUUID().toString());
        orders.setTotalNumber(ordersAddDTO.getGoodsOrderDTOList().size());
        return JSON.toJSONString(orderService.saveOrder(orders,ordersAddDTO.getGoodsOrderDTOList()));
    }

    @ApiOperation("更新状态")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", dataType = "int", required = true, value = "对应订单id", defaultValue = ""),
            @ApiImplicitParam(paramType = "query", name = "state", dataType = "int", required = true, value = "状态值", defaultValue = "")
    })
    @PostMapping("/changeState")
    public boolean changeState(@RequestParam String id,@RequestParam Integer state){

        QueryWrapper<Orders> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("id",id);

        Orders orders=new Orders();
        orders.setState(state);


        return orderService.update(orders,queryWrapper);
    }

}
