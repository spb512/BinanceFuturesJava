package com.binance.client.impl;

class ChannelParser {

    private String symbol = "";

    ChannelParser(String input) {
        String[] fields = input.split("\\.");
        if (fields.length >= 2) {
            symbol = fields[1];
        }
        String type = "";
        if (fields.length > 3) {
            type = fields[2];
        }
        if ("kline".equals(type)) {

        }
    }

    public String getSymbol() {
        return symbol;
    }
}
