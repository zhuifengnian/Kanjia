//
// Source mResponseCode recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.kanjia.basic;

public class ReturnMessage {
    private ResponseCode mResponseCode;
    private Object result;

    public ReturnMessage(){}

    public ReturnMessage(ResponseCode responseCode, Object result){
        this.mResponseCode = responseCode;
        this.result = result;
    }

    public ResponseCode getResponseCode() {
        return mResponseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.mResponseCode = responseCode;
    }

    public Object getResult() {
        return this.result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
