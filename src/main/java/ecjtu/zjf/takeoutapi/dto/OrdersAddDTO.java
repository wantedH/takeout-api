package ecjtu.zjf.takeoutapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrdersAddDTO {

    private String userToken;


    private Integer salerId;

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

    private List<GoodsOrderDTO> goodsOrderDTOList;
}
