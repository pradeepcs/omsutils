package org.csp.omsutils.interopservice.application;


import java.util.HashMap;
import java.util.Map;

import org.csp.omsutils.interopservice.httpclient.InteropClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App {

	public static ConfigurableApplicationContext context = null;

	static {
		loadAppProperties();
	}

	public static void loadAppProperties() {
		context = new ClassPathXmlApplicationContext("/spring/integration/http-outbound-config.xml");
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Hello World!");
		getOrderDetails();
	}

	private static void getOrderDetails() throws Exception {

		Map<String, String> requestMap = new HashMap<String, String>();
//		requestMap.put("YFSEnvironment.progId", "SterlingHttpTester");
//		requestMap.put("YFSEnvironment.userId", "admin");
//		requestMap.put("YFSEnvironment.password", "password");

		requestMap.put("InteropApiName", "getOrderDetails");
		requestMap.put("IsFlow", "N");
		requestMap.put("ApiName", "getOrderDetails");
		requestMap.put("ServiceName", "getOrderDetails");
		requestMap.put("InteropApiData", "<Order OrderHeaderKey='2016051601473258807' />");
		requestMap.put("TemplateData", "<Order OrderNo='' EnterpriseCode='' DocumentType='' />");

		InteropClient interopClient = context.getBean("requestGateway", InteropClient.class);
		System.out.println(interopClient.invokeApiOrService(requestMap));
	}

}
