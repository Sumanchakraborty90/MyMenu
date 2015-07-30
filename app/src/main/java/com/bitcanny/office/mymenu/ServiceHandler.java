package com.bitcanny.office.mymenu;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class ServiceHandler {

	static String response = null;
	
	public final static int GET = 1;
	public final static int POST = 2;

	public ServiceHandler(){
		
		
	}
	
	public String makeServiceCall(String url, int method) {
		return this.makeServiceCall(url, method, null);
	}
	
	
	public String makeServiceCall(String url, int method,
			List<NameValuePair> params) {
		
		
		try{
			
			DefaultHttpClient client = new DefaultHttpClient();
			HttpEntity entity = null;
			HttpResponse response1 = null;
			if(method == POST){
				HttpPost post = new HttpPost(url);
				
				if(params!=null){
					
					
					post.setEntity(new UrlEncodedFormEntity(params));
				}
				
				
				response1 = client.execute(post);
				
			}else if(method == GET){
				
				
				if(params !=null){
					
					
					String pramString = URLEncodedUtils.format(params, "utf-8");
					
					url+="?"+pramString;
				}
				
		HttpGet get = new HttpGet(url);
		
		response1 = client.execute(get);
				
			}
			
			
			
			entity = response1.getEntity();
			response = EntityUtils.toString(entity);
			
			
			
			
			
		}catch(Exception e){
			
			e.printStackTrace();
		}
     		return response;
		
	}
}

