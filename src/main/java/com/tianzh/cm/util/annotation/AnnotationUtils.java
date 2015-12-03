package com.tianzh.cm.util.annotation;

import com.tianzh.cm.annotation.Consumer;
import com.tianzh.cm.annotation.Kv;
import com.tianzh.cm.annotation.Xml;

import java.lang.reflect.Field;

/**
 * Author: cyc
 * Date: 12-3-19
 * Time: 上午10:23
 * Description: to write something
 */
public class AnnotationUtils {
    public static String getTopic(Class className) {
        if (className.isAnnotationPresent(Consumer.class)) {
            Consumer commandAnnotation = (Consumer) className.getAnnotation(Consumer.class);
            return commandAnnotation.value();
        } else {
            return null;
        }
    }

    public static String getXml(Class className) {
        if (className.isAnnotationPresent(Xml.class)) {
            Xml xml = (Xml) className.getAnnotation(Xml.class);
            return xml.key();
        }
        return null;
    }


    public static String getKey(Field field) {
        if (field.isAnnotationPresent(Kv.class)) {
            Kv kv = (Kv) field.getAnnotation(Kv.class);
            return kv.key();
        }
        return null;
    }
}
