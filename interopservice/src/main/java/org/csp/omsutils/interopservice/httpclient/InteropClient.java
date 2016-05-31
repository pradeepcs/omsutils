package org.csp.omsutils.interopservice.httpclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.csp.omsutils.interopservice.application.App;

public class InteropClient {

	
	/**
	 * 
	 * @param isFlow
	 * @param apiName
	 * @param inDoc
	 * @param template
	 * @return
	 * @throws Exception
	 */
	
	public static String invokeApiOrService(String isFlow, String apiName, String inDoc, String template) throws Exception {
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = InteropClient.getHttpPost(isFlow, apiName, inDoc, template);

		HttpResponse response = client.execute(post);
		System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
		        new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}	

		System.out.println("Response output : " + result);

		return result.toString();
	}
	
	
	/**
	 * 
	 * @param isFlow N/Y
	 * @param apiName - API or Service Name
	 * @param inDoc - API or Service input
	 * @param template - API output template
	 * @return
	 * @throws UnsupportedEncodingException
	 */

	public static HttpPost getHttpPost(String isFlow, String apiName, String inDoc, String template) throws UnsupportedEncodingException {

		HttpPost post = new HttpPost(App.prop.getProperty("interop_url"));
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("YFSEnvironment.progId", App.prop.getProperty("env_progId")));
		urlParameters.add(new BasicNameValuePair("InteropApiName", apiName));
		urlParameters.add(new BasicNameValuePair("IsFlow", isFlow));
		if("N".equals(isFlow)) {
			urlParameters.add(new BasicNameValuePair("ApiName", apiName));
		} else {
			urlParameters.add(new BasicNameValuePair("ServiceName", apiName));
		}
		urlParameters.add(new BasicNameValuePair("YFSEnvironment.userId", App.prop.getProperty("env_userId")));
		urlParameters.add(new BasicNameValuePair("YFSEnvironment.password", App.prop.getProperty("env_password")));
		urlParameters.add(new BasicNameValuePair("InteropApiData", inDoc));
		if(template != null) {
			urlParameters.add(new BasicNameValuePair("TemplateData", template));	
		}
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		return post;
	}

}
