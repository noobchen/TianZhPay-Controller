package com.tianzh.cm.network.http;

import org.apache.http.impl.nio.client.DefaultHttpAsyncClient;
import org.apache.http.nio.client.HttpAsyncClient;
import org.apache.http.nio.client.methods.AsyncCharConsumer;
import org.apache.http.nio.client.methods.HttpAsyncMethods;
import org.apache.http.nio.reactor.IOReactorException;

import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by cyc on 15-3-20.
 */
public class AsynchronousHttpRequest {

    private String url;
    private AsyncCharConsumer<String> consumer;

    public AsynchronousHttpRequest(String url, AsyncCharConsumer<String> consumer) {
        this.url = url;
        this.consumer = consumer;
    }

    public void asynchronousGetstandaloneSms() throws IOReactorException, InterruptedException, ExecutionException, TimeoutException {
        HttpAsyncClient httpClient = new DefaultHttpAsyncClient();

        httpClient.start();

        try {
            httpClient.execute(HttpAsyncMethods.createGet(this.url), this.consumer, null).get(10, TimeUnit.SECONDS);
        } finally {
            try {
                httpClient.shutdown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
