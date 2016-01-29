package sivl.platform.datebase.filter;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import sivl.platform.datebase.constant.CDataSourcesEnum;
import sivl.platform.datebase.extend3core.DataSourceContextHolder;

/**
 * 动态数据源切换拦截器
 * <br/>目的：
 * @author lixuefeng
 * @date 2016年1月29日
 * @version 1.0
 */
public class DynamicDataSourceInterceptor extends TransactionInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public Object invoke(final MethodInvocation invocation) throws Throwable {
		Class<?> targetClass = (invocation.getThis() != null ? AopUtils
				.getTargetClass(invocation.getThis()) : null);
		TransactionAttribute txAttr = getTransactionAttributeSource()
				.getTransactionAttribute(invocation.getMethod(), targetClass);
		//如果事物传播属性里设置readOnly为false就必须切换到主库的数据源以便支持事物，如果readOnly为true就用amoeba的数据源
		if (txAttr != null && !txAttr.isReadOnly()) {
			DataSourceContextHolder.setDataSourceType(CDataSourcesEnum.MYSQL_MASTER_DATA_SOURCE);
		} else {
			DataSourceContextHolder.setDataSourceType(CDataSourcesEnum.MYSQL_SLAVE_DATA_SOURCE);
		}
		return invokeWithinTransaction(invocation.getMethod(), targetClass,
				new InvocationCallback() {
			@Override
			public Object proceedWithInvocation() throws Throwable {
				return invocation.proceed();
			}
		});
	}
}
