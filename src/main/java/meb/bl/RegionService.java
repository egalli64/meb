package meb.bl;

import java.util.Optional;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import meb.dao.FakeRegionDao;
import meb.dao.Region;

@Stateless
@LocalBean
public class RegionService {
    private Logger LOG = LoggerFactory.getLogger(RegionService.class);

    @Inject
    private FakeRegionDao dao;

    public String getRegionName(String id) {
        try {
            Optional<Region> region = dao.read(Integer.valueOf(id));
            return region.isEmpty() ? "N/A" : region.get().getName();
        } catch (Exception ex) {
            LOG.warn("Can't get region " + id, ex);
            return "N/A";
        }
    }
}
