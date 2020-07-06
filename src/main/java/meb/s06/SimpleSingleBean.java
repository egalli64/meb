package meb.s06;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Session Bean implementation class Single
 */
@Singleton
@LocalBean
public class SimpleSingleBean {
    private Logger LOG = LoggerFactory.getLogger(InceptionBean.class);

    /**
     * Default constructor.
     */
    public SimpleSingleBean() {
        // TODO Auto-generated constructor stub
    }

    @PostConstruct
    public void init() {
        LOG.debug("starting up");
    }
    
    public String sayHello() {
        return "hello from singleton";
    }
}
