package meb.s06;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
@LocalBean
public class SimpleSTBean {
    private Logger LOG = LoggerFactory.getLogger(SimpleSTBean.class);

    public SimpleSTBean() {
    }

    @PostConstruct
    public void init() {
        LOG.debug("starting up");
    }

    public String sayHello() {
        LOG.debug("hello");
        return "hello from singleton";
    }
}
