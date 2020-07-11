package meb.m2;

import java.util.Arrays;
import java.util.List;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
@Path("region")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegionService {
    private final static Logger LOG = LoggerFactory.getLogger(RegionService.class);

    @GET
    @Path("{id}")
    public Region get(@PathParam("id") int id) {
        LOG.trace("get " + id);
        return new Region(id, "Fake");
    }

    @GET
    public List<Region> getAll() {
        LOG.trace("getAll");
        return Arrays.asList(new Region(1, "FakeOne"), new Region(2, "FakeTwo"));
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") int id) {
        LOG.trace("delete " + id);
    }

    @POST
    public Response save(Region region) {
        LOG.trace(String.format("Save %d, %s", region.getId(), region.getName()));
        if (region.getId() != 0) {
            return Response.ok().build();
        } else {
            // simulate creation of a new region
            region.setId(42);
            return Response.ok(region).build();
        }
    }

    @PUT
    public Response update(Region region) {
        LOG.trace(String.format("Save %d, %s", region.getId(), region.getName()));

        // simulate ensuring update vs create
        if (region.getId() == 42) {
            return Response.ok().build();
        } else {
            LOG.trace("not found");
            return Response.status(Status.NOT_FOUND).build();
        }
    }
}
