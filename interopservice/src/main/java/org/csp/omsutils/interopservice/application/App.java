package org.csp.omsutils.interopservice.application;

import java.util.Properties;

import org.csp.omsutils.interopservice.httpclient.InteropClient;

/**
 * Hello world!
 *
 */
public class App {
	
	public static Properties prop;
	
	static {
		loadAppProperties();
	}
	
	public static void loadAppProperties() {
		try {
			prop = new Properties();
			prop.load(App.class.getClassLoader().getResourceAsStream("customer_override.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
    public static void main( String[] args ) throws Exception {
        System.out.println( "Hello World!" );
        getOrderDetails();
    }

	private static void getOrderDetails() throws Exception {
		System.out.println(InteropClient.invokeApiOrService("N", "getOrderDetails", "<Order OrderHeaderKey='2016051601473258807' />", "<Order OrderNo='' />"));
	}

}
