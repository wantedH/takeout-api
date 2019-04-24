package ecjtu.zjf.takeoutapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ecjtu.zjf.takeoutapi.dto.OrderGoodsDTO;
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
    public List<OrderGoodsDTO> listFromOrder(String orderId) {
        return goodsMapper.selectFromOrder(orderId);
    }

    @Override
    public List<Goods> pageSaleGoods(int id, int nowPage) {
        Page<Goods> page = new Page<>();
        page.setCurrent(nowPage);
        page.setSize(2);

        QueryWrapper<Goods> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("saler_id",id);
        queryWrapper.orderByDesc("id");

        return goodsMapper.selectPage(page,queryWrapper).getRecords();
    }
}
