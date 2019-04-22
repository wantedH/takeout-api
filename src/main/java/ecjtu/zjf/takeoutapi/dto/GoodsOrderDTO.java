package ecjtu.zjf.takeoutapi.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("生成订单的商品和订单DTO")
public class GoodsOrderDTO {
    @ApiModelProperty(value = "商品id")
    private int goodsId;
    @ApiModelProperty(value = "对应订单id",hidden = true)
    private String orderId;
    @ApiModelProperty(value = "对应数量")
    private int num;
}
