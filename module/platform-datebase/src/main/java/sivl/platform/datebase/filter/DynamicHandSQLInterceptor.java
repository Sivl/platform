package sivl.platform.datebase.filter;

import java.sql.Connection;
import java.util.Properties;

import org.apache.commons.lang.reflect.FieldUtils;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

/**
 * 对sql语句或分表分库进行处理
 * <br/>目的：
 * @author lixuefeng
 * @date 2016年1月29日
 * @version 1.0
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class})})
public class DynamicHandSQLInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		
		if(invocation.getTarget() instanceof RoutingStatementHandler){
			//判断调用是否为路由声明处理
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
	        PreparedStatementHandler handler = (PreparedStatementHandler) FieldUtils.readDeclaredField(statementHandler, "delegate",true); 
	        MappedStatement mappedStatement = (MappedStatement)FieldUtils.readDeclaredField(handler, "mappedStatement", true);
	        //取得sql类型
	        SqlCommandType commandType = mappedStatement.getSqlCommandType();
	        //
		}
		return null;
	}

	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
		
	}

}
