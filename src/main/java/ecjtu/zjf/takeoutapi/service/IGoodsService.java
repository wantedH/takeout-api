package ecjtu.zjf.takeoutapi.service;

import ecjtu.zjf.takeoutapi.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import ecjtu.zjf.takeoutapi.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <p>
 * 菜品 服务类
 * </p>
 *
 * @author zjf
 * @since 2019-04-21
 */
public interface IGoodsService extends IService<Goods> {
    public List<Goods> listFromOrder(int orderId);

}
