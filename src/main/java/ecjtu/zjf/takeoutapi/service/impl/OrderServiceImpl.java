package ecjtu.zjf.takeoutapi.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ecjtu.zjf.takeoutapi.dto.GoodsOrderDTO;
import ecjtu.zjf.takeoutapi.dto.OrderGoodsDTO;

import ecjtu.zjf.takeoutapi.entity.Orders;
import ecjtu.zjf.takeoutapi.mapper.OrderMapper;
import ecjtu.zjf.takeoutapi.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author zjf
 * @since 2019-04-21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements IOrderService {


    @Autowired OrderMapper orderMapper;

    @Override
    public List<Orders> pageSaleGoods(String token, int nowPage) {
        Page<Orders> page = new Page<>();
        page.setCurrent(nowPage);
        page.setSize(2);

        QueryWrapper<Orders> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("user_token",token);

        return orderMapper.selectPage(page,queryWrapper).getRecords();
    }

    @Override
    public List<Orders> pageSalerOrders(int id, int nowPage) {
        Page<Orders> page = new Page<>();
        page.setCurrent(nowPage);
        page.setSize(2);

        QueryWrapper<Orders> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("saler_id",id);
        queryWrapper.orderByDesc("time");

        return orderMapper.selectPage(page,queryWrapper).getRecords();
    }

    @Transactional
    @Override
    public boolean saveOrder(Orders orders, List<GoodsOrderDTO> goodsOrderDTOS) {
        int res = orderMapper.insert(orders);
        if (res > 0) {
            for (GoodsOrderDTO goodsOrderDTO : goodsOrderDTOS) {
                goodsOrderDTO.setOrderId(orders.getId());
                if (orderMapper.insertOrderGoods(goodsOrderDTO) < 0) {
                    return false;
                }
            }
            return true;
        }

        return false;
    }
}
