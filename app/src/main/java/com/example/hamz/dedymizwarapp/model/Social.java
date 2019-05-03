package com.example.hamz.dedymizwarapp.model;

public class Social {

    String icon;
    String social;
    String link;

    public Social(String icon, String social, String link) {
        this.icon = icon;
        this.social = social;
        this.link = link;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
