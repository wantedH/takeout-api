package ecjtu.zjf.takeoutapi.dto;

import ecjtu.zjf.takeoutapi.entity.Goods;
import lombok.Data;

@Data
public class OrderGoodsDTO extends Goods {
    private int num;
}
