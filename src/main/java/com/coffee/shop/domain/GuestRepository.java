package com.coffee.shop.domain;

import com.coffee.shop.dto.CustomerForm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

/**
 * 游客
 *
 * @author waylon
 * @date 2018/07/30
 **/
@Repository
public interface GuestRepository {

    /**
     * 获取游客用户
     * @param openId
     * @return
     */
    Guest findGuestByOpenId(String openId);

}
