package ecjtu.zjf.takeoutapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import ecjtu.zjf.takeoutapi.entity.Saler;
import ecjtu.zjf.takeoutapi.mapper.SalerMapper;
import ecjtu.zjf.takeoutapi.service.ISalerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.Query;

/**
 * <p>
 * 商户信息 服务实现类
 * </p>
 *
 * @author zjf
 * @since 2019-04-21
 */
@Service
public class SalerServiceImpl extends ServiceImpl<SalerMapper, Saler> implements ISalerService {

    @Autowired SalerMapper salerMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<Saler> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",s);
        Saler saler = salerMapper.selectOne(queryWrapper);
        return saler;
    }
}
