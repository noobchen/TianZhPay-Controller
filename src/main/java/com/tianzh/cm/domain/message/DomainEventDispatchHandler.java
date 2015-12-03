package com.tianzh.cm.domain.message;

import com.tianzh.cm.container.ContainerWrapper;
import com.tianzh.cm.domain.consumer.ConsumerMethodHolder;
import com.tianzh.cm.event.disuptor.EventDisruptor;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: cyc
 * Date: 12-3-13
 * Time: 下午8:57
 * Description: to write something
 */
public class DomainEventDispatchHandler implements DomainEventHandler<EventDisruptor> {
    private final Logger logger = LoggerFactory.getLogger(DomainEventDispatchHandler.class);

    private ConsumerMethodHolder consumerMethodHolder;
    private ContainerWrapper containerWrapper;

    public DomainEventDispatchHandler(ConsumerMethodHolder consumerMethodHolder, ContainerWrapper containerWrapper) {
        super();
        this.consumerMethodHolder = consumerMethodHolder;
        this.containerWrapper = containerWrapper;
    }

    @Override
    public void onEvent(EventDisruptor event, final boolean endOfBatch) throws Exception {
        try {
            Method method = consumerMethodHolder.getMethod();
            Class[] pTypes = method.getParameterTypes();
            if (pTypes.length == 0) {
                logger.warn("consumer:{} method must have one parameter.", consumerMethodHolder);
                throw new Exception("method must have one parameter.");
            }
            Object parameter = event.getDomainMessage().getEventSource();
            if (parameter == null) {
                logger.error("the publisher method with @Send need return type.parameter:{},method:{}", pTypes, method);
                return;
            }

            Object[] parameters = new Object[pTypes.length];
            int i = 0;
            for (Class pType : pTypes) {
                if (pType.isAssignableFrom(parameter.getClass())) {
                    parameters[i] = parameter;
                } else {
                    if (!pType.isPrimitive())
                        parameters[i] = pType.newInstance();
                    else
                        parameters[i] = defaultValues.get(pType);
                }
                i++;
            }
            Object o = containerWrapper.lookupOriginal(consumerMethodHolder.getClassName());
            Object eventResult = method.invoke(o, parameters);

            if (method.getReturnType() != Void.class) {
                event.getDomainMessage().setEventResult(eventResult);
            }
        } catch (Exception e) {
            logger.error("method:{} with @onEvent error:{} ", consumerMethodHolder.getClassName(), ExceptionUtils.getStackTrace(e));      //产生异常直接返回，结束线程，防止线程阻塞。
        }
    }

    private final static Map<Class<?>, Object> defaultValues = new HashMap<Class<?>, Object>();

    static {
        defaultValues.put(String.class, "");
        defaultValues.put(Integer.class, 0);
        defaultValues.put(int.class, 0);
        defaultValues.put(Long.class, 0L);
        defaultValues.put(long.class, 0L);
        defaultValues.put(Character.class, '\0');
        defaultValues.put(char.class, '\0');
        // etc
    }
}
