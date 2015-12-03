package com.tianzh.cm.util.http;

import com.tianzh.cm.constant.SystemConstants;
import com.tianzh.cm.model.ClientRequestModel;

import com.tianzh.cm.model.PayOrder;
import com.tianzh.cm.util.encrypt.AESUtils;
import com.tianzh.cm.util.json.JsonUtils;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONTENT_LENGTH;
import static org.jboss.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static org.jboss.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static org.jboss.netty.handler.codec.http.HttpResponseStatus.OK;
import static org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * Author: cyc
 * Date: 12-3-20
 * Time: 上午12:11
 * Description: to write something
 */
public class HttpUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    private static final Logger httpResponseLogger = LoggerFactory.getLogger("http_response");
    private static boolean encrypt = true;

    public static void responseOrderError(PayOrder payOrder) {
        HashMap<String, String> responseMap = new HashMap<String, String>();

        responseMap.put("tianzhOrderId", payOrder.getTianzhOrderId());
        responseMap.put("statusCode", "404");
        responseMap.put("productId", payOrder.getProductId());
        responseMap.put("userOrderId", payOrder.getUserOrderId());
        responseMap.put("usertoken", payOrder.getUserToken());

        response(payOrder, responseMap);
    }

    public static boolean response(ClientRequestModel model, Object jsonObject) {
        String responseString = JsonUtils.objectToJson(jsonObject);

        try {
            if (encrypt) {
                response(model, AESUtils.encode(responseString));
            } else {
                response(model, responseString);
            }
            logger.debug("response:{} successful.request:{}", responseString, model);

            return true;
        } catch (Exception e) {
            logger.error("response:{} failure.exception:{}", responseString, ExceptionUtils.getStackTrace(e));
            httpResponseLogger.error("response:{} failure.exception:{}", responseString, ExceptionUtils.getStackTrace(e));

            return false;
        }
    }

    public static void response(ClientRequestModel model, String content) {
        // Decide whether to close the connection or not.
        HttpRequest request = (HttpRequest) model.getProperty(model.HTTP_REQUEST_KEY);
        boolean keepAlive = isKeepAlive(request);

        // Build the response object.
        HttpResponse response = new DefaultHttpResponse(HTTP_1_1, OK);

        response.setContent(ChannelBuffers.copiedBuffer(content, SystemConstants.DEFAULT_CHARSET));
        response.setHeader(CONTENT_TYPE, SystemConstants.DEFAULT_HTTP_CONTENT_TYPE);

        if (keepAlive) {
            // Add 'Content-Length' header only for a keep-alive connection.
            response.setHeader(CONTENT_LENGTH, response.getContent().readableBytes());
        }

        // Write the response.
        MessageEvent e = (MessageEvent) model.getProperty(model.MESSAGE_EVENT_KEY);
        ChannelFuture future = e.getChannel().write(response);

        // Close the non-keep-alive connection after the write operation is done.
        if (!keepAlive) {
            future.addListener(ChannelFutureListener.CLOSE);
        }

        logger.debug("response:{} successful.request:{}", content, request);
    }

    public void setEncrypt(boolean encrypt) {
        HttpUtils.encrypt = encrypt;
    }

}
