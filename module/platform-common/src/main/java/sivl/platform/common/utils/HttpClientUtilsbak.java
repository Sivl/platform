//package sivl.platform.common.utils;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.xml.ws.spi.http.HttpContext;
//
//import org.apache.http.Header;
//import org.apache.http.HeaderElement;
//import org.apache.http.HttpRequestInterceptor;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpResponseInterceptor;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.GzipDecompressingEntity;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpDelete;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpPut;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//import org.omg.DynamicAny.NameValuePair;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpRequest;
//
//import com.itbt.common.utils.StringUtils;
//
//public class HttpClientUtilsbak {
//
//	private static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
//	private static final String ENCODING_GZIP = "gzip";
//	private HttpClient httpclient;
//
//	/**
//	 * 通过post提交方式获取url指定的资源和数据
//	 * 
//	 * @param url
//	 * @return
//	 * @throws DajieHttpRequestException
//	 */
//	public String postData(String url) {
//		return postData(url, null);
//	}
//
//	/**
//	 * 通过post提交方式获取url指定的资源和数据
//	 * 
//	 * @param url
//	 * @param nameValuePairs
//	 * @return
//	 */
//	public String postData(String url, List<NameValuePair> nameValuePairs) {
//		return postData(url, nameValuePairs, null);
//	}
//
//	/**
//	 * 通过post提交方式获取url指定的资源和数据
//	 * 
//	 * @param url
//	 * @param nameValuePairs
//	 * @param headers
//	 * @return
//	 */
//	public String doPost(String url, Map<String, String> paramater,
//			String charSet) {
//		if (StringUtil.isEmpty(url)) {
//			return null;
//		}
//		try {
//			List<NameValuePair> pairs = null;
//			if (paramater != null && !paramater.isEmpty()) {
//				pairs = new ArrayList<NameValuePair>(paramater.size());
//				for (Map.Entry<String, String> entry : paramater.entrySet()) {
//					if (entry.getKey() == null)
//						continue;
//					pairs.add(new BasicNameValuePair(entry.getKey(), entry
//							.getValue()));
//				}
//			}
//			HttpPost httpPost = new HttpPost(url);
//			if (pairs != null && pairs.size() > 0) {
//				httpPost.setEntity(new UrlEncodedFormEntity(pairs, charSet));
//			}
//			CloseableHttpResponse response = httpClient.execute(httpPost);
//			int statusCode = response.getStatusLine().getStatusCode();
//			if (statusCode != 200) {
//				httpPost.abort();
//				throw new RuntimeException("HttpClient,error status code :"
//						+ statusCode);
//			}
//			HttpEntity entity = response.getEntity();
//			String result = null;
//			if (entity != null) {
//				result = EntityUtils.toString(entity, "utf-8");
//			}
//			EntityUtils.consume(entity);
//			response.close();
//			System.out.println(result);
//			return result;
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	/**
//	 * 通过ContentType 为json的格式进行http传输
//	 * 
//	 * @param url
//	 * @param content
//	 * @return
//	 */
//	public String postJSONData(String url, String content) {
//		long start = System.currentTimeMillis();
//		HttpPost httpPost = new HttpPost(url);
//		try {
//			if (content != null && content.length() > 0) {
//				httpPost.setEntity(new StringEntity(content,
//						ContentType.APPLICATION_JSON));
//			}
//			HttpResponse response = httpclient.execute(httpPost);
//			HttpEntity entity = (HttpEntity) response.getEntity();
//			if (entity == null) {
//				return null;
//			}
//			String info = EntityUtils.toString(entity, "UTF-8");
//			return info;
//		} catch (Exception e) {
//
//		} finally {
//			httpPost.releaseConnection();
//			long interval = System.currentTimeMillis() - start;
//
//		}
//	}
//
//	/**
//	 * 通过get方法获取url资源的数据
//	 * 
//	 * @param url
//	 * @return
//	 */
//	public String getData(String url) {
//		return getData(url, null);
//	}
//
//	/**
//	 * 带header的get请求
//	 * 
//	 * @param url
//	 * @param headers
//	 * @return
//	 */
//	public String getData(String url, Map<String, String> headers) {
//		long start = System.currentTimeMillis();
//		HttpGet httpGet = new HttpGet(url);
//		if (headers != null && headers.size() > 0) {
//			Set<Map.Entry<String, String>> set = headers.entrySet();
//			for (Iterator<Map.Entry<String, String>> it = set.iterator(); it
//					.hasNext();) {
//				Map.Entry<String, String> header = it.next();
//				if (header != null) {
//					httpGet.setHeader(header.getKey(), header.getValue());
//				}
//			}
//		}
//		try {
//			HttpResponse response = httpclient.execute(httpGet);
//			int status = response.getStatusLine().getStatusCode();
//			if (status == 404) {
//				return "404";
//			}
//			HttpEntity entity = response.getEntity();
//			if (entity == null) {
//				return null;
//			}
//			String info = EntityUtils.toString(entity, "UTF-8");
//			return info;
//		} catch (Exception e) {
//			logger.debug("getData Exception url: {}", url, e);
//			throw new DajieHttpRequestException(url
//					+ "dajie getData exception：", e);
//		} finally {
//			httpGet.releaseConnection();
//			long interval = System.currentTimeMillis() - start;
//			logger.debug("{} 请求耗时：{} ", url, interval);
//		}
//	}
//
//	/**
//	 * 对httpclient 做压缩处理和解压缩处理
//	 * 
//	 * @param httpclient
//	 */
//	public void initClient() {
//		((DefaultHttpClient) httpclient)
//				.addRequestInterceptor(new HttpRequestInterceptor() {
//					@Override
//					public void process(HttpRequest request, HttpContext context) {
//						if (!request.containsHeader(HEADER_ACCEPT_ENCODING)) {
//							request.addHeader(HEADER_ACCEPT_ENCODING,
//									ENCODING_GZIP);
//						}
//					}
//				});
//
//		((DefaultHttpClient) httpclient)
//				.addResponseInterceptor(new HttpResponseInterceptor() {
//					@Override
//					public void process(HttpResponse response,
//							HttpContext context) {
//						final HttpEntity entity = response.getEntity();
//						if (entity == null) {
//							return;
//						}
//						final Header encoding = entity.getContentEncoding();
//						if (encoding != null) {
//							for (HeaderElement element : encoding.getElements()) {
//								if (element.getName().equalsIgnoreCase(
//										ENCODING_GZIP)) {
//									response.setEntity(new GzipDecompressingEntity(
//											response.getEntity()));
//									break;
//								}
//							}
//						}
//					}
//				});
//	}
//
//	/**
//	 * 关闭客户端
//	 */
//	public void destroyClient() {
//		httpclient.getConnectionManager().shutdown();
//	}
//
//	/**
//	 * post方式处理文件和图片上传
//	 * 
//	 * @param url
//	 * @param data
//	 * @param fileName
//	 * @return
//	 */
//	public String postMultipartData(String url, byte[] data, String fileName) {
//		long start = System.currentTimeMillis();
//		HttpPost httpPost = new HttpPost(url);
//		try {
//			if (data != null && data.length > 0) {
//				MultipartEntity reqEntity = new MultipartEntity();
//				ContentBody contentBody = new ByteArrayBody(data, fileName);
//				reqEntity.addPart("file", contentBody);
//				httpPost.setEntity(reqEntity);
//			}
//			HttpResponse response = httpclient.execute(httpPost);
//			HttpEntity entity = response.getEntity();
//			String info = EntityUtils.toString(entity, "UTF-8");
//			return info;
//		} catch (Exception e) {
//			logger.debug("postMultipartData Exception url: {}", url, e);
//			throw new DajieHttpRequestException(url
//					+ "dajie postMultipartData exception：", e);
//		} finally {
//			httpPost.releaseConnection();
//			long interval = System.currentTimeMillis() - start;
//			logger.debug("{} 请求耗时：{} ", url, interval);
//		}
//	}
//
//	/**
//	 * put 方式提交数据
//	 * 
//	 * @param url
//	 * @param nameValuePairs
//	 * @return
//	 */
//	public String putData(String url, List<NameValuePair> nameValuePairs) {
//		long start = System.currentTimeMillis();
//		HttpPut httpPut = new HttpPut(url);
//
//		try {
//			if (nameValuePairs != null && nameValuePairs.size() > 0) {
//				httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairs,
//						"UTF-8"));
//			}
//			HttpResponse response = httpclient.execute(httpPut);
//			HttpEntity entity = response.getEntity();
//			if (entity == null) {
//				return null;
//			}
//			String info = EntityUtils.toString(entity, "UTF-8");
//			return info;
//		} catch (Exception e) {
//			logger.debug("putData Exception url:{}", url, e);
//			throw new DajieHttpRequestException(url
//					+ "dajie putData exception：", e);
//		} finally {
//			httpPut.releaseConnection();
//			long interval = System.currentTimeMillis() - start;
//			logger.debug("{} 请求耗时：{} ", url, interval);
//		}
//	}
//
//	/**
//	 * delete 方式提交数据
//	 * 
//	 * @param url
//	 * @return
//	 */
//	public String deleteData(String url) {
//		return deleteData(url, null);
//	}
//
//	/**
//	 * delete 方式提交数据
//	 * 
//	 * @param url
//	 * @param headers
//	 * @return
//	 */
//	public String deleteData(String url, Map<String, String> headers) {
//		long start = System.currentTimeMillis();
//		HttpDelete httpDelete = new HttpDelete(url);
//
//		if (headers != null && headers.size() > 0) {
//			Set<Map.Entry<String, String>> set = headers.entrySet();
//			for (Iterator<Map.Entry<String, String>> it = set.iterator(); it
//					.hasNext();) {
//				Map.Entry<String, String> header = it.next();
//				if (header != null) {
//					httpDelete.setHeader(header.getKey(), header.getValue());
//				}
//			}
//		}
//		try {
//			HttpResponse response = httpclient.execute(httpDelete);
//			HttpEntity entity = response.getEntity();
//			String info = EntityUtils.toString(entity, "UTF-8");
//			return info;
//		} catch (Exception e) {
//			logger.debug("putData Exception url {} ", url, e);
//			throw new DajieHttpRequestException(url
//					+ "dajie deleteDate exception：", e);
//		} finally {
//			httpDelete.releaseConnection();
//			long interval = System.currentTimeMillis() - start;
//			logger.debug("{} 请求耗时：{} ", url, interval);
//		}
//	}
//
//	/**
//	 * 下载媒体资源
//	 * 
//	 * @param url
//	 * @return
//	 */
//	public byte[] getMultipartData(String url) {
//		long start = System.currentTimeMillis();
//		HttpGet httpGet = new HttpGet(url);
//		try {
//			HttpResponse response = httpclient.execute(httpGet);
//			byte[] result = EntityUtils.toByteArray(response.getEntity());
//			return result;
//		} catch (Exception e) {
//			logger.debug("putData Exception url {} ", url, e);
//		} finally {
//			httpGet.releaseConnection();
//			long interval = System.currentTimeMillis() - start;
//			logger.debug("{} 请求耗时：{} ", url, interval);
//		}
//	}
//
//	public void setHttpclient(HttpClient httpclient) {
//		this.httpclient = httpclient;
//	}
//}
