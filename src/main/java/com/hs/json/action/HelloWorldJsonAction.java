package com.hs.json.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.Action;

public class HelloWorldJsonAction implements Action {
	
	private Map<String, String> JSONResult;
	private String result;

	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String reqjsonStr = request.getParameter("json");
		JSONObject reqjsonObj = JSONObject.fromObject(reqjsonStr);
		
		System.out.println("method:"+reqjsonObj.getString("method"));
		
		JSONObject reqParamsObj = JSONObject.fromObject(reqjsonObj.getString("params"));
		
		Map<String, String> tmp = new HashMap<String, String>();
		
		tmp.put("roomlist", reqParamsObj.getString("room"));
		tmp.put("memberlist", reqParamsObj.getString("member"));
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
