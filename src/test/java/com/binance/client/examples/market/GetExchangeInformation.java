package com.binance.client.examples.market;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;
import com.binance.client.examples.constants.PrivateConfig;
import com.binance.client.model.market.ExchangeInfoEntry;

import java.util.List;
import java.util.Map;

public class GetExchangeInformation {
    public static void main(String[] args) {
        RequestOptions options = new RequestOptions();
        options.setUrl("https://testnet.binancefuture.com");
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);
        List<ExchangeInfoEntry> symbols = syncRequestClient.getExchangeInformation().getSymbols();
        for(ExchangeInfoEntry exchangeInfoEntry : symbols){
            if("BTCUSDT".equals(exchangeInfoEntry.getSymbol())){
                System.out.println(exchangeInfoEntry);
                List<List<Map<String, String>>> filters = exchangeInfoEntry.getFilters();
                List<Map<String, String>> mapList = filters.get(2);
                    String maxQty = mapList.get(2).get("maxQty");
                    System.out.println(maxQty);
            }
        }
        //[symbol=BTCUSDT,status=TRADING,maintMarginPercent=2.5,requiredMarginPercent=5,baseAsset=BTC,quoteAsset=USDT,pricePrecision=2,quantityPrecision=3,baseAssetPrecision=8,quotePrecision=8,orderTypes=[LIMIT, MARKET, STOP, STOP_MARKET, TAKE_PROFIT, TAKE_PROFIT_MARKET, TRAILING_STOP_MARKET],timeInForce=[LIMIT, MARKET, STOP, STOP_MARKET, TAKE_PROFIT, TAKE_PROFIT_MARKET, TRAILING_STOP_MARKET],filters=[[{minPrice=556.80}, {maxPrice=4529764}, {filterType=PRICE_FILTER}, {tickSize=0.10}], [{stepSize=0.001}, {filterType=LOT_SIZE}, {maxQty=1000}, {minQty=0.001}], [{stepSize=0.001}, {filterType=MARKET_LOT_SIZE}, {maxQty=120}, {minQty=0.001}], [{limit=200}, {filterType=MAX_NUM_ORDERS}], [{limit=10}, {filterType=MAX_NUM_ALGO_ORDERS}], [{notional=5}, {filterType=MIN_NOTIONAL}], [{multiplierDown=0.9500}, {multiplierUp=1.0500}, {multiplierDecimal=4}, {filterType=PERCENT_PRICE}]]]
        //System.out.println(syncRequestClient.getExchangeInformation());
    }
}
