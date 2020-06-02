package meb.s05;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateful
@LocalBean
public class CounterSFBean {
	private Logger LOG = LoggerFactory.getLogger(CounterSFBean.class);

	private int counter = 0;

	public int increase() {
		LOG.trace("counter was " + counter);
		counter += 1;
		return counter;
	}
}
