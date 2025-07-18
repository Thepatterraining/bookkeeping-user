package com.zt.bookkeeping.user.domain.user.respository;

import com.zt.bookkeeping.user.domain.user.entity.UserAgg;

public interface UserRepository {

    UserAgg getUser(String username);

    UserAgg getUserByMobile(String mobile);

    void insert(UserAgg userAgg);
}
