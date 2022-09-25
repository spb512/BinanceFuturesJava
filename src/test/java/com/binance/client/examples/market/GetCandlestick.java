package com.binance.client.examples.market;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;
import com.binance.client.examples.constants.PrivateConfig;
import com.binance.client.model.enums.CandlestickInterval;
import com.binance.client.model.market.Candlestick;

import java.util.Arrays;
import java.util.List;

public class GetCandlestick {
    public static void main(String[] args) {
        double[] dClose = new double[500];
        RequestOptions options = new RequestOptions();
        options.setUrl("https://testnet.binancefuture.com");
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);
        List<Candlestick> candlestickList = syncRequestClient.getCandlestick("BTCUSDT", CandlestickInterval.THREE_MINUTES, null, null, 500);
//        System.out.println(syncRequestClient.getCandlestick("BTCUSDT", CandlestickInterval.THREE_MINUTES, null, null, 250));
        for (int i = 0; i < candlestickList.size(); i++){
            dClose[i] = candlestickList.get(i).getClose().doubleValue();
        }
        System.out.println(Arrays.toString(dClose));
    }
}
