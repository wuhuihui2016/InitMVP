package com.whh.initmvp.model;

import java.util.List;

/**
 * Created by wuhuihuhi on 2019/5/16.
 * AppVersion 满足Gson解析的数据结构
 */

public class AppVersion {

    /**
     * http://ios.mobile.che-by.com/appversion-getAppVersion?currentVersion=6.0.2&type=android
     *
     * {
     "size": "14MB",
     "functions": ["1.上线新活动\n2.部分页面优化更新\n3.修复已知BUG"],
     "latestVersion": "6.0.4",
     "must": -1,
     "url": "http://download.fengyangtech.com/apk/ChebyMall.apk"
     }
     */

    private String size;
    private List<String> functions;
    private String latestVersion;
    private int must;
    private String url;

    public AppVersion(String size, List<String>  functions,
                      String latestVersion, int must, String url) {
        this.size = size;
        this.functions = functions;
        this.latestVersion = latestVersion;
        this.must = must;
        this.url = url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public List<String> getFunctions() {
        return functions;
    }

    public void setFunctions(List<String> functions) {
        this.functions = functions;
    }

    public String getLatestVersion() {
        return latestVersion;
    }

    public void setLatestVersion(String latestVersion) {
        this.latestVersion = latestVersion;
    }

    public int getMust() {
        return must;
    }

    public void setMust(int must) {
        this.must = must;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "AppVersion{" +
                "size='" + size + '\'' +
                ", functions=" + functions +
                ", latestVersion='" + latestVersion + '\'' +
                ", must=" + must +
                ", url='" + url + '\'' +
                '}';
    }

}
