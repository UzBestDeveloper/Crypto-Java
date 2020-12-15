package com.developer.crypto.connect.models.metrics;

import java.io.Serializable;

public class MarketData  implements Serializable {
    private double price_usd;
    private double percent_change_usd_last_24_hours;

    public double getPercent_change_usd_last_24_hours() {
        return percent_change_usd_last_24_hours;
    }

    public double getPrice_usd() {
        return price_usd;
    }
}
