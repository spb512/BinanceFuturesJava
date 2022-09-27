package com.binance.client.examples.trade;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;
import com.binance.client.examples.constants.PrivateConfig;
import com.binance.client.model.trade.Asset;
import com.binance.client.model.trade.Position;

import java.util.List;

public class GetAccountInformation {
    public static void main(String[] args) {
        RequestOptions options = new RequestOptions();
        options.setUrl("https://testnet.binancefuture.com");
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);
        while (true){
            List<Position> positionList = syncRequestClient.getAccountInformation().getPositions();
            List<Asset> assets = syncRequestClient.getAccountInformation().getAssets();
            for(Asset asset:assets){
                if("USDT".equals(asset.getAsset())){
                    System.out.println(asset);
                    break;
                }
            }
            for (Position position:positionList) {
                if ("BTCUSDT".equals(position.getSymbol())){
                    System.out.println(position);
//                    System.out.println(position.getUnrealizedProfit().subtract(position.getMaintMargin()));
                    break;
                }
            }
        }
    }
}