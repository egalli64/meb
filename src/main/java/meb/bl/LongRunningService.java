package meb.bl;

import java.util.concurrent.Future;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@LocalBean
public class LongRunningService {
    Logger LOG = LoggerFactory.getLogger(LongRunningService.class);

    @Asynchronous
    public void fireAndForget() {
        LOG.trace("enter");
        // simulating a long job
        try {
            Thread.sleep(1000);
            LOG.info("Job completed");
        } catch (InterruptedException ie) {
            LOG.warn("Job interrupted", ie);
        }
    }

    @Asynchronous
    public void fireAndForgetEx() {
        LOG.trace("enter");
        // simulating a long job that goes wrong
        try {
            Thread.sleep(1000);
            throw new IllegalStateException("Boom!");
        } catch (InterruptedException ie) {
            LOG.warn("Job interrupted", ie);
        } finally {
            LOG.trace("exit");
        }
    }

    public String synchronous() {
        LOG.trace("enter");
        // simulating a long job
        try {
            Thread.sleep(1000);
            LOG.info("Job completed");
        } catch (InterruptedException ie) {
            LOG.warn("Job interrupted", ie);
        }
        return "synchronous";
    }

    @Asynchronous
    public Future<String> fireAndCheck() {
        LOG.trace("enter");
        // simulating a long job
        try {
            Thread.sleep(1000);
            LOG.info("Job completed");
        } catch (InterruptedException ie) {
            LOG.warn("Job interrupted", ie);
        }
        return new AsyncResult<String>("Future result");
    }

    @Asynchronous
    public Future<String> fireAndCheckEx() {
        LOG.trace("enter");
        // simulating a long job
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ie) {
            LOG.warn("Job interrupted", ie);
        }
        throw new IllegalStateException("Boom!");
    }
}
