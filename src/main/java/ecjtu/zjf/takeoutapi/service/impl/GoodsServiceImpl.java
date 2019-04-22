package ecjtu.zjf.takeoutapi.service.impl;

import ecjtu.zjf.takeoutapi.entity.Goods;
import ecjtu.zjf.takeoutapi.mapper.GoodsMapper;
import ecjtu.zjf.takeoutapi.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜品 服务实现类
 * </p>
 *
 * @author zjf
 * @since 2019-04-21
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired GoodsMapper goodsMapper;

    @Override
    public List<Goods> listFromOrder(int orderId) {
        return goodsMapper.selectFromOrder(orderId);
    }
}
