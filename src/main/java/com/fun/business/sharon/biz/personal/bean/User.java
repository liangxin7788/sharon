package com.fun.business.sharon.biz.personal.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author liangxin
 * @since 2019-05-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String password;

    private String username;

    private String encryptedPassword;

    private String encryptedUsername;

    private String imaccount;

    private Long employee;

    private Long manager;

    private Boolean inactive;

    private Long customerserviceId;

    private String groupname;

    private Boolean isshowvendorinfo;

    private Long salechannelaccountgroupId;

    private LocalDateTime lastlogintime;


}
