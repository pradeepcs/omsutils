package org.csp.omsutils.interopservice.httpclient;

import java.util.Map;

public interface InteropClient {

	String invokeApiOrService(Map<String, String> request);

}
