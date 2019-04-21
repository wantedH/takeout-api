package ecjtu.zjf.takeoutapi.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商户信息
 * </p>
 *
 * @author zjf
 * @since 2019-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Saler implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String name;

    private String location;

    private Integer tel;

}
