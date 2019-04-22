package ecjtu.zjf.takeoutapi.service.impl;

import ecjtu.zjf.takeoutapi.entity.Orders;
import ecjtu.zjf.takeoutapi.mapper.OrderMapper;
import ecjtu.zjf.takeoutapi.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
