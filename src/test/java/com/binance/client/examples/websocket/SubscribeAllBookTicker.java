package com.binance.client.examples.websocket;

import com.binance.client.SubscriptionClient;

public class SubscribeAllBookTicker {

    public static void main(String[] args) {

        SubscriptionClient client = SubscriptionClient.create();

        client.subscribeAllBookTickerEvent(((event) -> {
            System.out.println(event);
        }), null);

    }

}
