package com.binance.client.impl;

import com.binance.client.exception.BinanceApiException;
import com.binance.client.impl.utils.JsonWrapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

abstract class RestApiInvoker {

    private static final Logger log = LoggerFactory.getLogger(RestApiInvoker.class);
    private static final OkHttpClient client = new OkHttpClient();
//    private static final OkHttpClient client = new OkHttpClient.Builder().proxy(new Proxy(Proxy.Type.HTTP,
//            new InetSocketAddress("127.0.0.1", 10809))).build();
    private static final Dispatcher dispatcher = new Dispatcher();
    private static final OkHttpClient wsClient = new OkHttpClient().newBuilder().dispatcher(dispatcher).pingInterval(30, TimeUnit.SECONDS).build();

    static {
        dispatcher.setMaxRequests(3000000);
        dispatcher.setMaxRequestsPerHost(1000000);
    }

    static void checkResponse(JsonWrapper json) {
        try {
            if (json.containKey("success")) {
                boolean success = json.getBoolean("success");
                if (!success) {
                    String err_code = json.getStringOrDefault("code", "");
                    String err_msg = json.getStringOrDefault("msg", "");
                    if ("".equals(err_code)) {
                        throw new BinanceApiException(BinanceApiException.EXEC_ERROR, "[Executing] " + err_msg);
                    } else {
                        throw new BinanceApiException(BinanceApiException.EXEC_ERROR,
                                "[Executing] " + err_code + ": " + err_msg);
                    }
                }
            } else if (json.containKey("code")) {

                int code = json.getInteger("code");
                if (code != 200) {
                    String message = json.getStringOrDefault("msg", "");
                    throw new BinanceApiException(BinanceApiException.EXEC_ERROR,
                            "[Executing] " + code + ": " + message);
                }
            }
        } catch (BinanceApiException e) {
            throw e;
        } catch (Exception e) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Invoking] Unexpected error: " + e.getMessage());
        }
    }

    static <T> T callSync(RestApiRequest<T> request) {
        try {
            String str;
            log.debug("Request URL " + request.request.url());
            Response response = client.newCall(request.request).execute();
            // System.out.println(response.body().string());
            if (response != null && response.body() != null) {
                str = response.body().string();
//                log.info("Request URL：" + request.request.url());
//                log.info("x-mbx-used-weight-1m：" + response.header("x-mbx-used-weight-1m"));
                response.close();
            } else {
                throw new BinanceApiException(BinanceApiException.ENV_ERROR,
                        "[Invoking] Cannot get the response from server");
            }
            log.debug("Response =====> " + str);
            JsonWrapper jsonWrapper = JsonWrapper.parseFromString(str);
            checkResponse(jsonWrapper);
            return request.jsonParser.parseJson(jsonWrapper);
        } catch (BinanceApiException e) {
            throw e;
        } catch (Exception e) {
            throw new BinanceApiException(BinanceApiException.ENV_ERROR,
                    "[Invoking] Unexpected error: " + e.getMessage());
        }
    }

    static WebSocket createWebSocket(Request request, WebSocketListener listener) {
        return wsClient.newWebSocket(request, listener);
    }

}
