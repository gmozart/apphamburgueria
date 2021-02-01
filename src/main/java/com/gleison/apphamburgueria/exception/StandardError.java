package com.gleison.apphamburgueria.exception;

public class StandardError {

    private Integer erro;
    private String msg;
    private Long timeStamp;



    public StandardError() {
    }

    public StandardError(Integer erro, String msg, Long timeStamp) {
        this.erro = erro;
        this.msg = msg;
        this.timeStamp = timeStamp;
    }

    public Integer getErro() {
        return erro;
    }

    public void setErro(Integer erro) {
        this.erro = erro;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
