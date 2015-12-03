package com.tianzh.cm.network.http;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by cyc on 15-3-21.
 */
public class HttpClientRequest {


    public static String getCode(String url) throws IOException {
        String result = "";

        HttpClient client = new DefaultHttpClient();

        HttpGet get = new HttpGet(url);

        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
                5 * 1000);
        // 读取超时
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
                10 * 1000);

        try {
            HttpResponse httpResponse = client.execute(get);

            int rc = httpResponse.getStatusLine().getStatusCode();

            if (rc != HttpURLConnection.HTTP_OK) {
                result = "";
            }

            HttpEntity entity = httpResponse.getEntity();

            if (entity != null) {
                result = EntityUtils.toString(entity);
            }

        }  finally {
            //释放连接
            client.getConnectionManager().shutdown();
            get.abort();
        }

        return result;
    }
}
