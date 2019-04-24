package ecjtu.zjf.takeoutapi.service;

import ecjtu.zjf.takeoutapi.dto.GoodsOrderDTO;
import ecjtu.zjf.takeoutapi.dto.OrderGoodsDTO;

import ecjtu.zjf.takeoutapi.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author zjf
 * @since 2019-04-21
 */
public interface IOrderService extends IService<Orders> {

     List<Orders> pageSaleGoods(String token, int nowPage);
     List<Orders> pageSalerOrders(int id,int nowPage);
     boolean saveOrder(Orders orders, List<GoodsOrderDTO> goodsOrderDTOS);

}
