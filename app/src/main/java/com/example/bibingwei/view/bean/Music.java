package com.example.bibingwei.view.bean;

/**
 * @author bibingwei
 */
public class Music {

    /**
     * success : true
     * url : http://dl.stream.qqmusic.qq.com/M800003OUlho2HcRHC.mp3?vkey=AB90BFFDF7F7F6BADFFB5015228A97EE5E4F4BA8CBA5779F89F917C69F1FEA7EABB60E8F34EC687DD2AFAD5CF3A6DC57EF9CBCDC98F0A598&guid=355890383&fromtag=30
     */

    private boolean success;
    private String url;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
