package com.binance.client.model.enums;

import com.binance.client.impl.utils.EnumLookup;

/**
 * buy-market, sell-market, buy-limit, buy-ioc, sell-ioc,
 * buy-limit-maker, sell-limit-maker, buy-stop-limit, sell-stop-limit.
 */
public enum OrderType {
    LIMIT("LIMIT"),
    MARKET("MARKET"),
    STOP("STOP"),
    STOP_MARKET("STOP_MARKET"),
    TAKE_PROFIT("TAKE_PROFIT"),
    TAKE_PROFIT_MARKET("TAKE_PROFIT_MARKET"),
    TRAILING_STOP_MARKET("TAKE_PROFIT_MARKET");

    private static final EnumLookup<OrderType> lookup = new EnumLookup<>(OrderType.class);
    private final String code;

    OrderType(String code) {
        this.code = code;
    }

    public static OrderType lookup(String name) {
        return lookup.lookup(name);
    }

    @Override
    public String toString() {
        return code;
    }

}
