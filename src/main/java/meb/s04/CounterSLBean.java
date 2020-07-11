package meb.s04;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@LocalBean
public class CounterSLBean {
	private Logger LOG = LoggerFactory.getLogger(CounterSLBean.class);

	// !!! Stateless EB with a state !!!
	private int counter = 0;

	public int increase() {
		LOG.error("Stateless EB changes its state! Don't do that in real code!");
		counter += 1;
		return counter;
	}
}
