package com.hs.json.action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

public class HelloWorldJsonAction implements Action {
	
	private Map<String, String> JSONResult;
	private String result;

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		Map<String, String> tmp = new HashMap<String, String>();
		tmp.put("roomlist", "room");
		tmp.put("memberlist", "member");
		JSONObject jo = JSONObject.fromObject(tmp);
		this.result = jo.toString();
		this.JSONResult = tmp;
		
		return SUCCESS;
	}
	
	public Map<String, String> getJSONResult(){
		return JSONResult;
	}

	public void setJSONResult(Map<String, String> jSONResult) {
		JSONResult = jSONResult;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
