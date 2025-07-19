package com.zt.bookkeeping.user.domain.generator;

public interface SnowFlakeGenerator {
    String nextId(String businessType);
}
