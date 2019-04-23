package ecjtu.zjf.takeoutapi.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author zjf
 * @since 2019-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 买家信息
     */
    private String userToken;


    private Integer salerId;

    /**
     * 商品总数
     */
    private Integer totalNumber;

    /**
     * 总价
     */
    private Double totalPrice;

    /**
     * 买家电话
     */

    private String userTel;


    /**
     * 送达地址
     */
    private String userLocation;

    /**
     * 买家姓名
     */
    private String userName;

    /**
     * 下单时间
     */
    private LocalDateTime time;


}
