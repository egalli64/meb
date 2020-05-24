package meb.bl;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@Startup
@LocalBean
public class Inception {
	private Logger LOG = LoggerFactory.getLogger(Inception.class);

	@PostConstruct
	public void init() {
		LOG.info("starting up");
	}
}
