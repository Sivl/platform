package sivl.platform.datebase.extend3core;

import static org.apache.ibatis.reflection.ExceptionUtil.unwrapThrowable;
import static org.mybatis.spring.SqlSessionUtils.closeSqlSession;
import static org.mybatis.spring.SqlSessionUtils.getSqlSession;
import static org.mybatis.spring.SqlSessionUtils.isSqlSessionTransactional;

import java.lang.reflect.InvocationHandler;

import static java.lang.reflect.Proxy.newProxyInstance;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.executor.BatchResult;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.MyBatisExceptionTranslator;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.util.Assert;

/**
 * 自定义扩展sql session template
 * <br/>目的：
 * @author lixuefeng
 * @date 2016年1月29日
 * @version 1.0
 */
public class CustomSqlSessionTemplate extends SqlSessionTemplate {
	
	private final SqlSessionFactory sqlSessionFactory;
    private final ExecutorType executorType;
    private final SqlSession sqlSessionProxy;
    private final PersistenceExceptionTranslator exceptionTranslator;
 
    private Map<Object, SqlSessionFactory> targetSqlSessionFactorys;
    private SqlSessionFactory defaultTargetSqlSessionFactory;
 
    public void setTargetSqlSessionFactorys(Map<Object, SqlSessionFactory> targetSqlSessionFactorys) {
        this.targetSqlSessionFactorys = targetSqlSessionFactorys;
    }
 
    public void setDefaultTargetSqlSessionFactory(SqlSessionFactory defaultTargetSqlSessionFactory) {
        this.defaultTargetSqlSessionFactory = defaultTargetSqlSessionFactory;
    }
 
    public CustomSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        this(sqlSessionFactory, sqlSessionFactory.getConfiguration().getDefaultExecutorType());
    }
 
    public CustomSqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType) {
        this(sqlSessionFactory, executorType, new MyBatisExceptionTranslator(sqlSessionFactory.getConfiguration()
                .getEnvironment().getDataSource(), true));
    }
 
    public CustomSqlSessionTemplate(SqlSessionFactory sqlSessionFactory, ExecutorType executorType,
            PersistenceExceptionTranslator exceptionTranslator) {
 
        super(sqlSessionFactory, executorType, exceptionTranslator);
 
        this.sqlSessionFactory = sqlSessionFactory;
        this.executorType = executorType;
        this.exceptionTranslator = exceptionTranslator;
        
        this.sqlSessionProxy = (SqlSession) newProxyInstance(
                SqlSessionFactory.class.getClassLoader(),
                new Class[] { SqlSession.class }, 
                new SqlSessionInterceptor());
 
        this.defaultTargetSqlSessionFactory = sqlSessionFactory;
    }
 
	@Override
    public SqlSessionFactory getSqlSessionFactory() {
 
        SqlSessionFactory targetSqlSessionFactory = targetSqlSessionFactorys.get(DataSourceContextHolder.getDataSourceType().getValue());
        if (targetSqlSessionFactory != null) {
            return targetSqlSessionFactory;
        } else if (defaultTargetSqlSessionFactory != null) {
            return defaultTargetSqlSessionFactory;
        } else {
            Assert.notNull(targetSqlSessionFactorys, "Property 'targetSqlSessionFactorys' or 'defaultTargetSqlSessionFactory' are required");
            Assert.notNull(defaultTargetSqlSessionFactory, "Property 'defaultTargetSqlSessionFactory' or 'targetSqlSessionFactorys' are required");
        }
        return this.sqlSessionFactory;
    }
 
    @Override
    public Configuration getConfiguration() {
        return this.getSqlSessionFactory().getConfiguration();
    }
 
    public ExecutorType getExecutorType() {
        return this.executorType;
    }
 
    public PersistenceExceptionTranslator getPersistenceExceptionTranslator() {
        return this.exceptionTranslator;
    }
    
    @Override
	public <T> T selectOne(String statement) {
		return this.sqlSessionProxy.<T>selectOne(statement);
	}

	@Override
	public <T> T selectOne(String statement, Object parameter) {
		return this.sqlSessionProxy.<T>selectOne(statement, parameter);
	}

	@Override
	public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
		return this.sqlSessionProxy.<K, V>selectMap(statement, mapKey);
	}

	@Override
	public <K, V> Map<K, V> selectMap(String statement, Object parameter,
			String mapKey) {
		return this.sqlSessionProxy.<K, V>selectMap(statement, parameter, mapKey);
	}

	@Override
	public <K, V> Map<K, V> selectMap(String statement, Object parameter,
			String mapKey, RowBounds rowBounds) {
		return this.sqlSessionProxy.<K, V>selectMap(statement, parameter, mapKey, rowBounds);
	}

	@Override
	public <E> List<E> selectList(String statement) {
		return this.sqlSessionProxy.<E>selectList(statement);
	}

	@Override
	public <E> List<E> selectList(String statement, Object parameter) {
		return this.sqlSessionProxy.<E>selectList(statement, parameter);
	}

	@Override
	public <E> List<E> selectList(String statement, Object parameter,
			RowBounds rowBounds) {
		return this.sqlSessionProxy.<E>selectList(statement, parameter, rowBounds);
	}

	@Override
	public void select(String statement, ResultHandler handler) {
		this.sqlSessionProxy.select(statement, handler);
	}

	@Override
	public void select(String statement, Object parameter, ResultHandler handler) {
		this.sqlSessionProxy.select(statement, parameter, handler);
	}

	@Override
	public void select(String statement, Object parameter, RowBounds rowBounds,
			ResultHandler handler) {
		this.sqlSessionProxy.select(statement, parameter, rowBounds, handler);
	}

	@Override
	public int insert(String statement) {
		return this.sqlSessionProxy.insert(statement);
	}

	@Override
	public int insert(String statement, Object parameter) {
		return this.sqlSessionProxy.insert(statement, parameter);
	}

	@Override
	public int update(String statement) {
		return this.sqlSessionProxy.update(statement);
	}

	@Override
	public int update(String statement, Object parameter) {
		return this.sqlSessionProxy.update(statement, parameter);
	}

	@Override
	public int delete(String statement) {
		return this.sqlSessionProxy.delete(statement);
	}

	@Override
	public int delete(String statement, Object parameter) {
		return this.sqlSessionProxy.delete(statement, parameter);
	}

	@Override
	public <T> T getMapper(Class<T> type) {
		return this.sqlSessionProxy.<T>getMapper(type);
	}

	@Override
	public void commit() {
		this.sqlSessionProxy.commit();
	}

	@Override
	public void commit(boolean force) {
		this.sqlSessionProxy.commit(force);
	}

	@Override
	public void rollback() {
		super.rollback();
	}

	@Override
	public void rollback(boolean force) {
		super.rollback(force);
	}

	@Override
	public void close() {
		this.sqlSessionProxy.close();
	}

	@Override
	public void clearCache() {
		this.sqlSessionProxy.clearCache();
	}

	@Override
	public Connection getConnection() {
		return this.sqlSessionProxy.getConnection();
	}

	@Override
	public List<BatchResult> flushStatements() {
		return this.sqlSessionProxy.flushStatements();
	}



	/**
     * Proxy needed to route MyBatis method calls to the proper SqlSession got
     * from Spring's Transaction Manager
     * It also unwraps exceptions thrown by {@code Method#invoke(Object, Object...)} to
     * pass a {@code PersistenceException} to the {@code PersistenceExceptionTranslator}.
     */
    private class SqlSessionInterceptor implements InvocationHandler {
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SqlSession sqlSession = getSqlSession(
        		CustomSqlSessionTemplate.this.sqlSessionFactory,
        		CustomSqlSessionTemplate.this.executorType,
        		CustomSqlSessionTemplate.this.exceptionTranslator);
        try {
          Object result = method.invoke(sqlSession, args);
          if (!isSqlSessionTransactional(sqlSession, CustomSqlSessionTemplate.this.sqlSessionFactory)) {
            // force commit even on non-dirty sessions because some databases require
            // a commit/rollback before calling close()
            sqlSession.commit(true);
          }
          return result;
        } catch (Throwable t) {
          Throwable unwrapped = unwrapThrowable(t);
          if (CustomSqlSessionTemplate.this.exceptionTranslator != null && unwrapped instanceof PersistenceException) {
            // release the connection to avoid a deadlock if the translator is no loaded. See issue #22
            closeSqlSession(sqlSession, CustomSqlSessionTemplate.this.sqlSessionFactory);
            sqlSession = null;
            Throwable translated = CustomSqlSessionTemplate.this.exceptionTranslator.translateExceptionIfPossible((PersistenceException) unwrapped);
            if (translated != null) {
              unwrapped = translated;
            }
          }
          throw unwrapped;
        } finally {
          if (sqlSession != null) {
            closeSqlSession(sqlSession, CustomSqlSessionTemplate.this.sqlSessionFactory);
          }
        }
      }
    }

}
