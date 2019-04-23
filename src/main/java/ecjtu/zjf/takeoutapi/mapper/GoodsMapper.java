package ecjtu.zjf.takeoutapi.mapper;

import ecjtu.zjf.takeoutapi.dto.OrderGoodsDTO;
import ecjtu.zjf.takeoutapi.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 菜品 Mapper 接口
 * </p>
 *
 * @author zjf
 * @since 2019-04-21
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    @Select("SELECT * FROM goods right join order_goods_rel on goods_id=goods.id where order_id = #{id}")
    List<OrderGoodsDTO> selectFromOrder(String orderId);
}
