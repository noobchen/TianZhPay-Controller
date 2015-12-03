package com.tianzh.cm.event.disuptor;

import com.lmax.disruptor.ExceptionHandler;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IgnoreExceptionHandler implements ExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(IgnoreExceptionHandler.class);

    @Override
    public void handleEventException(Throwable ex, long sequence, Object event) {
        logger.info("Exception processing: sequence:{},event:{},ex:{}", new Object[]{sequence, event, ExceptionUtils.getStackTrace(ex)});
    }

    @Override
    public void handleOnStartException(Throwable ex) {
        logger.info("Exception:{} during onStart()", ex);
    }

    @Override
    public void handleOnShutdownException(Throwable ex) {
        logger.info("Exception:{} during onShutdown()", ex);
    }
}
