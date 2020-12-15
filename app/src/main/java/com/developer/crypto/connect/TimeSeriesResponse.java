package com.developer.crypto.connect;

import java.io.Serializable;
import java.util.List;

public class TimeSeriesResponse implements Serializable {

    private List<List<Float>> values;

    public List<List<Float>> getValues() {
        return values;
    }
}
