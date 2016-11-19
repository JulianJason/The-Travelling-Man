package com.lejit.thetravellingman.Model;

/**
 * Created by USER on 11/18/2016.
 */

public class RssData {
    private String title;
    private String desc;
    private String link;

    public RssData() {
    }

    public RssData(String title, String desc, String link) {
        this.title = title;
        this.desc = desc;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
