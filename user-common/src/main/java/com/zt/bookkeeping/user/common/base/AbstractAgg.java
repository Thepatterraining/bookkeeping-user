package com.zt.bookkeeping.user.common.base;

import java.util.List;

public abstract class AbstractAgg {
    private List<DomainEvent> domainEvents;

    /**
     * 注册领域事件
     * @param event
     */
    public void registerDomainEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    /**
     * 清空领域事件
     */
    public void clearDomainEvents() {
        domainEvents.clear();
    }

    /**
     * 获取领域事件
     * @return
     */
    public List<DomainEvent> getDomainEvents() {
        return domainEvents;
    }
}
