package com.modubiz.modu.common.util;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.net.ssl.SSLContext;
import java.net.URI;
import java.nio.charset.Charset;
import java.security.cert.X509Certificate;
import java.text.MessageFormat;
import java.util.*;

public class HttpClient {

	private final static String CHARSET = Charset.defaultCharset().displayName();
	
	protected static Logger logger = (Logger) LoggerFactory.getLogger(HttpClient.class);

	//final static String ERR_GET_MAX_LENGTH = "경고: GET 방식의 255Byte가 넘는경우 구형 서버에서 대응하지 못할 수 있습니다. (MSIE와 Safari에서 약 2KB, Opera에서 약 4KB, Firefox에서 약 8KB)";

	private HttpHeaders			headers				= null;
	private Map<String, String> param				= null;
	private String 				body				= null;
	private LinkedMultiValueMap<String, String> bodyMap		= null;
	private URI					uri					= null;
	private int					timeout				= 10;
	private int					readTimeout			= 30;
	private boolean				isSSL				= false;

	private Exception 			exception			= null;
	private String				errorCode			= null;
	private String 				errorMessage		= null;
	private String				statusCode			= null;
	private String				statusMessage		= null;
	private int					maxConnPerRoute		= 1000;
	private int					maxConnTotal		= 10000;

	private ResponseEntity<String> response			= null;
	
	public static HttpClient newHttp() {
		return new HttpClient();
	}
	
	public HttpClient Body(String body) {
		this.body = body;
		return this;
	}
	
	public HttpClient Body(LinkedMultiValueMap<String, String> bodyMap) {
		this.bodyMap = bodyMap;
		return this;
	}
	
	public HttpClient Body(Map<String, String> bodyMap) {
		this.bodyMap = new LinkedMultiValueMap<>();
		for(Map.Entry<String, String> ent : bodyMap.entrySet()) {
			this.bodyMap.add(ent.getKey(), ent.getValue());
		}
		return this;
	}
	
	public HttpClient JsonBody(Object body) throws Exception {
		this.body = getConvertJson(body);
		return this;
	}
	public HttpClient JsonBody(String body) throws Exception {
		this.body = body;
		return this;
	}

	public HttpClient JsonXml(Object body) throws Exception {
		this.body = getConvertXml(body);
		return this;
	}
	
	public static String getConvertJson(Object obj) throws Exception {
		return (new ObjectMapper()).writeValueAsString(obj);
	}
	
	public static String getConvertXml(Object obj) throws Exception {
		return (new XmlMapper()).writeValueAsString(obj);
	}
	
	public HttpClient Param(Map<String,String> param) {
		this.param = param;
		return this;
	}
	
	public HttpClient Timeout(int timeout) {
		this.timeout = timeout;
		return this;
	}
	
	public HttpClient ReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
		return this;
	}
	
	public HttpClient IsSSL(boolean isSSL) {
		this.isSSL = isSSL;
		return this;
	}

	public HttpClient Header(HttpHeaders headers) {
		this.headers = headers;
		return this;
	}
	
	public HttpClient newHeader() {
		this.headers = new HttpHeaders();
		return this;
	}
	
	public HttpClient setHeader(String headerName, @Nullable String headerValue) {
		this.headers.set(headerName, headerValue);
		return this;
	}

	public HttpClient setContentType(MediaType mediaType) {
		this.headers.setContentType(mediaType);
		return this;
	}
	
	public HttpHeaders getHttpHeaders() {
		return this.headers;
	}
	
	public URI getURI() {
		return this.uri;
	}
	
	public ResponseEntity<String> response() {
		return this.response;
	}
	
	public String responseBody() {
		return (this.response==null?null:this.response.getBody());
	}
	
	public String errorCode() {
		return this.errorCode;
	}
	
	public String errorMessage() {
		return this.errorMessage;
	}
	
	public String statusCode() {
		return this.statusCode;
	}
	
	public String statusMessage() {
		return this.statusMessage;
	}

	private void setErrorCode(String errorCode, String errorMessage) {
		this.errorCode 		= errorCode;
		this.errorMessage 	= errorMessage;
	}
	
	private void setStatusCode(String statusCode, String statusMessage) {
		this.statusCode 	= statusCode;
		this.statusMessage 	= statusMessage;
	}

	public Exception exception() {
		return this.exception;
	}
	
	public HttpClient sendGet(String urlString, String charset) {
		return sendHttp(urlString, charset, HttpMethod.GET);
	}
	
	public HttpClient sendPost(String urlString, String charset) {
		return sendHttp(urlString, charset, HttpMethod.POST);
	}
	
	public HttpClient newParam() {
		this.param = new LinkedHashMap<String,String>();
		return this;
	}
	
	public Map<String,String> getParam() {
		return this.param;
	}
	
	public HttpClient setParam(String key, String value) {
		this.param.put(key, value);
		return this;
	}
	
	private void sendInit() {
		this.uri	 		= null;
		this.exception 		= null;
		this.response		= null;
		this.errorCode 		= null;
		this.errorMessage 	= null;
		this.statusCode 	= null;
		this.statusMessage 	= null;
	}
	
	public String getErrorMessage() {
		return MessageFormat.format("errorCode={0},errorMessage={1},statusCode={2},statusMessage={3}", this.errorCode, this.errorMessage, this.statusCode, this.statusMessage);
	}
	
	private RestTemplate getRestTemplate () throws Exception {
		RestTemplate restTemplate 		= null;
		HttpComponentsClientHttpRequestFactory requestFactory = null;

		if ( this.isSSL || this.timeout>0 ) {
			requestFactory = new HttpComponentsClientHttpRequestFactory();
		}
		if ( this.isSSL ) {
			// SSL Custom Port Support
			TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
			SSLContext sslContext = SSLContexts.custom()
					.loadTrustMaterial(acceptingTrustStrategy)
					.build();
			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

			CloseableHttpClient httpClient = HttpClients.custom()
					.setMaxConnPerRoute(maxConnPerRoute)
					.setMaxConnTotal(maxConnTotal)
					.setSSLSocketFactory(csf)
					.build();
			
			requestFactory.setHttpClient(httpClient);
		}
		if ( this.timeout>0 ) {	
			requestFactory.setConnectTimeout(this.timeout*1000);
		}
		if ( this.readTimeout>0 ) {
			requestFactory.setReadTimeout(this.timeout*1000);
		}
		if ( requestFactory != null ) {
			restTemplate = new RestTemplate(requestFactory);
		} else {
			restTemplate = new RestTemplate();
		}
		return  restTemplate;
	}
	
	private HttpClient sendHttp(String urlString, String charset, HttpMethod method) {
		if(charset==null) charset = CHARSET;
		sendInit();
		try {
			this.isSSL = urlString.toLowerCase().startsWith("https");
			RestTemplate restTemplate = this.getRestTemplate();
			restTemplate.setErrorHandler(new RestTemplateErrorHandler());
			
			//Create a list for the message converters
			List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
			//Add the String Message converter
			messageConverters.add(new FormHttpMessageConverter());
			messageConverters.add(new StringHttpMessageConverter(Charset.forName(charset)));
			//Add the message converters to the restTemplate
			restTemplate.setMessageConverters(messageConverters);
			
			if (headers == null) {
				headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
			}
			if (headers.getContentType().toString().toUpperCase().indexOf("JSON") > -1) {
				messageConverters.add(new MappingJackson2HttpMessageConverter(new ObjectMapper()));
			}
			HttpEntity<?> request = null;
			this.uri = getUri(urlString.trim(), this.param, null, charset);
			request = new HttpEntity<>((this.bodyMap != null ? this.bodyMap : this.body), headers);
			//			logger.debug(uri.toString());
			this.response = restTemplate.exchange(this.uri, method, request, String.class);
		} catch (HttpClientErrorException.NotFound ex) {
			setErrorCode("9001", ex.getMessage());
			setStatusCode(ex.getRawStatusCode()+"", ex.getStatusText());
			this.exception = ex;
			ex.printStackTrace();
		} catch (ConnectTimeoutException ste) {
			setErrorCode("9002", ste.getMessage());
			this.exception = ste;
			ste.printStackTrace();
		} catch (ResourceAccessException se) {
			setErrorCode("9003", se.getMessage());
			this.exception = se;
			se.printStackTrace();
		} catch (Exception ex) {
			setErrorCode("9000", ex.getMessage());
			this.exception = ex;
			ex.printStackTrace();
		} finally {
			if(this.errorCode==null) {
				if(this.response == null) {
					setErrorCode("9008", "서버 통신 오류!!");
				} else if(this.response.getStatusCodeValue()!=200) {
					setErrorCode("9" + this.response.getStatusCodeValue(), this.response.getStatusCode().toString());
					setStatusCode(this.response.getStatusCodeValue()+"", this.response.getStatusCode().toString());
				} else if(this.response != null && this.response.getBody() == null) {
					setErrorCode("9007", "내용을 정상적으로 수신하지 못함.");
				}
			}
		}
		return this;
	}
	
	public URI getUri(String url, Map<String,String> queryParamMap, Map<String,String> urlParamMap) throws Exception {
		return getUri(url, queryParamMap, urlParamMap, null);
	}
	
	public URI getUri(String url, Map<String,String> queryParamMap, Map<String,String> urlParamMap, String charset) throws Exception {
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
		if(queryParamMap!=null) {		// {a=1,b=2}	ex)	https://abcd/?a=1&b=2	
			for(Map.Entry<String, String> ent : queryParamMap.entrySet()) {
				builder.queryParam(ent.getKey(), ent.getValue());
			}
		}
		UriComponents uc = null;
		uc = (urlParamMap==null?builder.build():builder.buildAndExpand(urlParamMap));	// {a=1,b=2},https://abcd/{a}/{b} ex) https://abcd/1/2
		uc = (charset!=null?uc.encode(Charset.forName(charset)) : uc.encode(Charset.defaultCharset()));
		
		return uc.toUri();
	}
}

