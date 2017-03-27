package transage.com.aidl_client.bean;

import java.util.List;

/**
 * Created by dongrp on 2016/9/28.
 */
public class CarBean {
    /**
     * timestamp : 1475042504755
     * resultData : []
     * resultCode : OK
     */

    private long timestamp;
    private String resultCode;
    private List<?> resultData;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public List<?> getResultData() {
        return resultData;
    }

    public void setResultData(List<?> resultData) {
        this.resultData = resultData;
    }

    @Override
    public String toString() {
        return "CarBean{" +
                "timestamp=" + timestamp +
                ", resultCode='" + resultCode + '\'' +
                ", resultData=" + resultData +
                '}';
    }
}
