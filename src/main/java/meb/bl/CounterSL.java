package meb.bl;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@LocalBean
public class CounterSL {
	private Logger LOG = LoggerFactory.getLogger(CounterSL.class);

	private int counter = 0;

	public int increase() {
		LOG.trace("counter was " + counter);
		counter += 1;
		return counter;
	}
}
