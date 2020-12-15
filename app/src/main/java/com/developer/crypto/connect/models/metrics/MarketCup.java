package com.developer.crypto.connect.models.metrics;

import java.io.Serializable;
import java.math.BigInteger;

public class MarketCup implements Serializable {
    private double current_marketcap_usd;

    public double getCurrent_marketcap_usd() {
        return current_marketcap_usd;
    }
}
