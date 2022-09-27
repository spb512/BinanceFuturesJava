package com.binance.client.examples.trade;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;
import com.binance.client.examples.constants.PrivateConfig;
import com.binance.client.model.trade.PositionRisk;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GetPositionRisk {
    public static void main(String[] args) {
        RequestOptions options = new RequestOptions();
        options.setUrl("https://testnet.binancefuture.com");
        while (true){
            SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                    options);
//            System.out.println(syncRequestClient.getPositionRisk("btcusdt"));
            PositionRisk positionRisk = syncRequestClient.getPositionRisk("btcusdt").get(0);
            BigDecimal unrealizedProfit = positionRisk.getUnrealizedProfit();
            BigDecimal isolatedMargin = new BigDecimal(positionRisk.getIsolatedMargin());
            BigDecimal isolated = isolatedMargin.subtract(unrealizedProfit);
            BigDecimal uplRatio = unrealizedProfit.divide(isolated, 2, RoundingMode.HALF_UP);
            System.out.println("UnrealizedProfit:{"+positionRisk.getUnrealizedProfit() +"};uplRatio:{"+ uplRatio +"}");
        }
    }
}
