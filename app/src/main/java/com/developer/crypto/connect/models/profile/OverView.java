package com.developer.crypto.connect.models.profile;

import java.io.Serializable;
import java.util.List;

public class OverView implements Serializable {
    private String project_details;
    private String tagline;
    private List<OfficialLink> official_links;

    public String getProject_details() {
        return project_details;
    }

    public String getTagline() {
        return tagline;
    }

    public List<OfficialLink> getOfficial_links() {
        return official_links;
    }
}
