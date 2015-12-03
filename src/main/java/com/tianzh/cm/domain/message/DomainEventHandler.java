package com.tianzh.cm.domain.message;

import com.tianzh.cm.event.disuptor.EventDisruptor;

/**
 * Author: cyc
 * Date: 12-3-13
 * Time: 下午8:56
 * Description: to write something
 */
public interface DomainEventHandler<T> {
    void onEvent(EventDisruptor event, final boolean endOfBatch) throws Exception;
}
