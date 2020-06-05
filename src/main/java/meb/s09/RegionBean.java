package meb.s09;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@LocalBean
public class RegionBean {
    static private final Logger LOG = LoggerFactory.getLogger(RegionBean.class);

    @Resource(name = "jdbc/me")
    private DataSource ds;

    public String getRegionName(int id) {
        try (Connection conn = ds.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("SELECT region_name FROM regions WHERE region_id = " + id)) {
            rs.next();
            return rs.getString(1);
        } catch (SQLException se) {
            LOG.warn("Can't get region " + id, se);
            return "N/A";
        }
    }
}
