package meb.s06;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Startup
@DependsOn("InceptionBean")
@LocalBean
public class CounterSTBean {
	private Logger LOG = LoggerFactory.getLogger(CounterSTBean.class);

	@PostConstruct
	public void init() {
		LOG.debug("CounterST available");
	}
	
	private int counter = 0;

	public int increase() {
		LOG.trace("counter was " + counter);
		counter += 1;
		return counter;
	}
}
