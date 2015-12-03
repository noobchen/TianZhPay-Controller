package com.tianzh.cm.event.disuptor;

import com.tianzh.cm.domain.message.DomainMessage;


public interface DisruptorListener {
    void action(DomainMessage domainMessage);
}
