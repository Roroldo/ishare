package com.roroldo.ishare.domain;

/**
 * 封装后端返回前端的数据对象
 * @author 落霞不孤
 */
public class ResultInfo {
    /**
     * 后端返回结果正常为true，发生异常返回false
     */
    private boolean flag;
    /**
     * 后端返回的数据结果
     */
    private Object data;
    /**
     * 错误信息
     */
    private String errorMsg;

    public ResultInfo() {
    }

    public ResultInfo(boolean flag) {
        this.flag = flag;
    }

    public ResultInfo(boolean flag, String errorMsg) {
        this.flag = flag;
        this.errorMsg = errorMsg;
    }

    public ResultInfo(boolean flag, Object data, String errorMsg) {
        this.flag = flag;
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
