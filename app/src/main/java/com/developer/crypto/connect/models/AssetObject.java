package com.developer.crypto.connect.models;

import com.developer.crypto.connect.models.profile.Profile;
import com.developer.crypto.connect.models.metrics.Metrics;

import java.io.Serializable;

public class AssetObject implements Serializable {
    private String id;
    private String symbol;
    private String name;
    private String slug;
    private String _internal_temp_agora_id;
    Metrics metrics;
    Profile profile;

    public Profile getProfile() {
        return profile;
    }

    public Metrics getMetrics() {
        return metrics;
    }

    public String getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getSlug() {
        return slug;
    }

    public String get_internal_temp_agora_id() {
        return _internal_temp_agora_id;
    }
}
