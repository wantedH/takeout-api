package ecjtu.zjf.takeoutapi.service;

import ecjtu.zjf.takeoutapi.entity.Saler;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>
 * 商户信息 服务类
 * </p>
 *
 * @author zjf
 * @since 2019-04-21
 */
public interface ISalerService extends IService<Saler>,UserDetailsService {



}
