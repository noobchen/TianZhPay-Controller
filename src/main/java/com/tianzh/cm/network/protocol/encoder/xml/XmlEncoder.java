package com.tianzh.cm.network.protocol.encoder.xml;

import com.tianzh.cm.constant.SystemConstants;
import com.tianzh.cm.network.protocol.encoder.Encoder;
import com.tianzh.cm.util.xml.XmlUtils;

/**
 * Author: cyc
 * Date: 12-4-27
 * Time: 下午1:59
 * Description: to write something
 */
public class XmlEncoder implements Encoder<Object> {
    private String charset = SystemConstants.DEFAULT_ENCODE;

    @Override
    public String encode(Object o) throws Exception {
        return XmlUtils.objectToXml(o, charset);
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }
}
