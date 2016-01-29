package sivl.platform.redis.service.impl;import java.util.ArrayList;import java.util.Date;import java.util.List;import java.util.Set;import java.util.concurrent.TimeUnit;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.redis.core.ListOperations;import org.springframework.data.redis.core.RedisTemplate;import org.springframework.stereotype.Service;import sivl.platform.common.model.PageModel;import sivl.platform.common.utils.DateUtil;import sivl.platform.common.utils.StringUtil;import sivl.platform.redis.service.RedisServic;/** * redis服务实现<br/> * 目的： * @author lixuefeng * @createTime 2015年12月23日 * @version 1.0 */@Service("redisServic")public class RedisServicHandleImpl<V> implements RedisServic<V> {	@Autowired	RedisTemplate<String, V> redisTemplate;		@Override	public V saveObject(String prefixKey, String suffixKey, int timeout, V value) {		String keyStr = getRedisKey(prefixKey, suffixKey);		redisTemplate.opsForValue().set(keyStr, value, timeout,TimeUnit.MINUTES);		return value;	}	@Override	public V saveObjectSameDay(String prefixKey, String suffixKey, V value) {		String keyStr = getRedisKey(prefixKey, suffixKey);		long timeout = getTimeOut();		redisTemplate.opsForValue().set(keyStr, value, timeout,TimeUnit.MILLISECONDS);		return value;	}	@Override	public V getObjectByKey(String prefixKey, String suffixKey) {		String keyStr = getRedisKey(prefixKey, suffixKey);		return redisTemplate.opsForValue().get(keyStr);	}	@Override	public V saveObjectInQueue(String prefixKey, String suffixKey, int timeout,			V value) {		String keyStr = getRedisKey(prefixKey, suffixKey);		redisTemplate.opsForList().rightPush(keyStr, value);		redisTemplate.expire(keyStr, timeout, TimeUnit.MINUTES);		return value;	}	@Override	public V getObjectInQueue(String prefixKey, String suffixKey) {		String keyStr = getRedisKey(prefixKey, suffixKey);		return redisTemplate.opsForList().leftPop(keyStr);	}	@Override	public V saveObjectInStack(String prefixKey, String suffixKey, int timeout,			V value) {		String keyStr = getRedisKey(prefixKey, suffixKey);		redisTemplate.opsForList().rightPush(keyStr, value);		redisTemplate.expire(keyStr, timeout, TimeUnit.MINUTES);		return value;	}	@Override	public V getObjectInStack(String prefixKey, String suffixKey) {		String keyStr = getRedisKey(prefixKey, suffixKey);		return redisTemplate.opsForList().rightPop(keyStr);	}	@SuppressWarnings("unchecked")	@Override	public void saveSet(String prefixKey, String suffixKey, int timeout,			V... values) {		String keyStr = getRedisKey(prefixKey, suffixKey);		redisTemplate.opsForSet().add(keyStr, values);		redisTemplate.expire(keyStr, timeout, TimeUnit.MINUTES);	}	@Override	public Set<V> getSet(String prefixKey, String suffixKey) {		String keyStr = getRedisKey(prefixKey, suffixKey);		return redisTemplate.opsForSet().members(keyStr);	}	@Override	public V saveMap(String prefixKey, String suffixKey, int timeout,			String hashKey, V value) {		String keyStr = getRedisKey(prefixKey, suffixKey);		redisTemplate.opsForHash().put(keyStr, hashKey, value);		redisTemplate.expire(keyStr, timeout, TimeUnit.MINUTES);		return value;	}	@Override	public V saveObjectInList(String prefixKey, String suffixKey, int timeout,			V value) {		String keyStr = getRedisKey(prefixKey, suffixKey);		redisTemplate.opsForList().rightPush(keyStr, value);		redisTemplate.expire(keyStr, timeout, TimeUnit.MINUTES);		return value;	}	@Override	public List<V> getList(String prefixKey, String suffixKey) {		String keyStr = getRedisKey(prefixKey, suffixKey);		long size = redisTemplate.opsForList().size(keyStr);		return redisTemplate.opsForList().range(keyStr, 0, size);	}		@Override	public List<V> getRangeList(String prefixKey, String suffixKey, int start,			int end) {		String keyStr = getRedisKey(prefixKey, suffixKey);		return redisTemplate.opsForList().range(keyStr, start, end);	}	@Override	public PageModel<V> getPageList(String prefixKey, String suffixKey,			PageModel<V> page) {		String keyStr = getRedisKey(prefixKey, suffixKey);		ListOperations<String, V> listOperations = redisTemplate.opsForList(); 		int count = listOperations.size(keyStr).intValue();		int pageCount = (int)Math.ceil(((double)count/page.getPageSize()));		int startCount = (1-page.getPageNum())*page.getPageSize();		int endCount = startCount+page.getPageSize()-1;		List<V> list = listOperations.range(keyStr, startCount, endCount);		if(list==null){			list = new ArrayList<V>();		}		page.setPageCount(pageCount);		page.setTotalRow(count);		page.setResult(list);		page.setReturnRow(list.size());		return page;	}	@Override	public void deleteByKey(String prefixKey, String suffixKey) {		String keyStr = getRedisKey(prefixKey, suffixKey);		redisTemplate.delete(keyStr);	}		@Override	public void removeListValue(String prefixKey,String suffixKey,Long count,V value) {		String keyStr = getRedisKey(prefixKey, suffixKey);		redisTemplate.opsForList().remove(keyStr, count, value);	}	//获取redis对应的key	private String getRedisKey(String prefixKey,String suffixKey){		return (StringUtil.isNotEmpty(prefixKey) ? prefixKey : "")+"_"+(StringUtil.isNotEmpty(suffixKey) ? suffixKey : "");	}		//获取从现在到当天23:59:59剩下还有多少毫秒	private long getTimeOut(){		Date currDate = new Date();		String currDateStr = DateUtil.fmtDateToStr(currDate,"yyyy-MM-dd");		Date sameDay = DateUtil.fmtStrToDate(currDateStr+" 23:59:59", "yyyy-MM-dd HH:mm:ss");		long timeout = sameDay.getTime()-currDate.getTime();		return timeout;	}}