package sivl.safety.tasks.jobs;import org.quartz.JobExecutionContext;import org.quartz.JobExecutionException;import org.springframework.scheduling.quartz.QuartzJobBean;/** * http远程调用任务<br/> * 目的：用于定时调用远程接口 * @author lixuefeng * @createTime 2015年11月21日 * @version 1.0 */public class HttpRMIJob extends QuartzJobBean{	private int timeout;    /**     * Setter called after the ExampleJob is instantiated     * with the value from the JobDetailFactoryBean (5)     */    public void setTimeout(int timeout) {        this.timeout = timeout;    }		@Override	protected void executeInternal(JobExecutionContext arg0)			throws JobExecutionException {		System.out.println("--------------------------------------");			}}