package ecjtu.zjf.takeoutapi.mapper;

import ecjtu.zjf.takeoutapi.dto.GoodsOrderDTO;
import ecjtu.zjf.takeoutapi.dto.OrderGoodsDTO;
import ecjtu.zjf.takeoutapi.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author zjf
 * @since 2019-04-21
 */
public interface OrderMapper extends BaseMapper<Orders> {

    @Insert("INSERT INTO `takeout`.`order_goods_rel` (`order_id`, `goods_id`, `num`) VALUES (#{orderId},#{goodsId} ,#{num}) ")
    int insertOrderGoods(GoodsOrderDTO goodsOrderDTO);
}
