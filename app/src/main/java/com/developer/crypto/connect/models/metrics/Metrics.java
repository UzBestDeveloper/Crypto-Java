package com.developer.crypto.connect.models.metrics;

import java.io.Serializable;

public class Metrics implements Serializable {
    private MarketData market_data;
    private MarketCup marketcap;

    public MarketData getMarket_data() {
        return market_data;
    }

    public MarketCup getMarketcap() {
        return marketcap;
    }
}
