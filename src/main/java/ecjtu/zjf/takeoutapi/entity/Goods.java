package ecjtu.zjf.takeoutapi.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 菜品
 * </p>
 *
 * @author zjf
 * @since 2019-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private Integer salerId;

    private String name;

    private Double price;

    private String doc;

    /**
     * 是否还在提供
     */
    private Boolean state;

    private String avatar;

}
