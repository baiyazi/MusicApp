package com.weizu.mymusicdemo.fragments.beans;

public class FxPageSongListItemBean {
    private String coverImageUrl;
    private String playNumber;
    private String introduceInfo;

    public FxPageSongListItemBean(String coverImageUrl, String playNumber, String introduceInfo) {
        this.coverImageUrl = coverImageUrl;
        this.playNumber = playNumber;
        this.introduceInfo = introduceInfo;
    }

    public FxPageSongListItemBean() {
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public String getPlayNumber() {
        return playNumber;
    }

    public void setPlayNumber(String playNumber) {
        this.playNumber = playNumber;
    }

    public String getIntroduceInfo() {
        return introduceInfo;
    }

    public void setIntroduceInfo(String introduceInfo) {
        this.introduceInfo = introduceInfo;
    }
}
