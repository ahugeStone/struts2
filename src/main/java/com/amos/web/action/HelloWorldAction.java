package com.amos.web.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.opensymphony.xwork2.ActionSupport;

public class HelloWorldAction extends ActionSupport{
    private static final long serialVersionUID = 6872366878758961847L;
    //重写execute()方法
    
    public InputStream getHello() throws UnsupportedEncodingException{
    	return new ByteArrayInputStream("{\"name\":123}".getBytes("UTF-8")) ;
    }
    
    public String execute() throws Exception {
        System.out.println("欢迎使用struts2!");
        return SUCCESS;
    }
}
