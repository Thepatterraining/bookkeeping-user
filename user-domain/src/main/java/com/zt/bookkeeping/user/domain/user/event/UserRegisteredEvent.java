package com.zt.bookkeeping.user.domain.user.event;

import com.zt.bookkeeping.user.common.base.AbstractEvent;
import com.zt.bookkeeping.user.domain.user.entity.UserAgg;
import lombok.Getter;

import java.time.LocalDateTime;


public class UserRegisteredEvent extends AbstractEvent<UserAgg> {

    public UserRegisteredEvent(UserAgg userAgg) {
        super(userAgg, "UserRegisteredEvent");
    }

}
