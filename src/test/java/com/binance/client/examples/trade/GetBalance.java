package com.binance.client.examples.trade;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;
import com.binance.client.examples.constants.PrivateConfig;
import com.binance.client.exception.BinanceApiException;

public class GetBalance {
    public static void main(String[] args) {
        RequestOptions options = new RequestOptions();
        options.setUrl("https://testnet.binancefuture.com");
        try {
            SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                    options);
            System.out.println(syncRequestClient.getBalance());
        } catch (BinanceApiException e){
            System.out.println(e.getErrType());
            System.out.println(e.getMessage().split(":")[0]);
        }
    }
}