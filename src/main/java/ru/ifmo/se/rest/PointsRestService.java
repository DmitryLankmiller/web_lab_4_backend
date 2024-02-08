package ru.ifmo.se.rest;

import java.util.List;

import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import ru.ifmo.se.points.AreaCheckBean;
import ru.ifmo.se.points.Point;
import ru.ifmo.se.points.PointsContainerBean;

@Path("/points")
@Singleton
public class PointsRestService {

    @EJB(name = "points")
    PointsContainerBean points;

    @EJB(name = "area")
    AreaCheckBean areaCheckBean;

    
    @GET
    @Path("/echo/{echo}")
    @Produces("text/html")
    public String echo(@PathParam("echo") String echo, @Context HttpServletRequest request) {
        var userName = getUserName(request);
        return "<h1>'" + echo + "', said " + userName + "</h1>";
    }
    
    @GET
    @Path("/all")
    @Produces("application/json")
    public List<Point> getPointsByUserId(@Context HttpServletRequest request) {
        var userName = getUserName(request);
        return points.getPointsByUserName(userName);
    }
    
    @POST
    @Path("/shot")
    @Produces("application/json")
    public Point shot(@QueryParam("X") double x, @QueryParam("Y") double y, @QueryParam("R") int r,
            @Context HttpServletRequest request) {
        Point p = areaCheckBean.calculate(x, y, r);
        p.setUserName(getUserName(request));
        points.addPoint(p);
        return p;
    }

    @DELETE
    @Path("/clear")
    public void clear(@Context HttpServletRequest request) {
        var userName = getUserName(request);
        points.clearPointsByUserName(userName);
    }
    
    @GET
    @Path("/logout")
    public void logout(@Context HttpServletRequest request) throws ServletException {
        request.logout();
    }
    
    private String getUserName(HttpServletRequest request) {
        var userName = request.getUserPrincipal().getName();
        if (userName == null) {
            userName = "DefaultUser";
        }
        return userName;
    }
    
    public PointsRestService() {
        points = new PointsContainerBean();
        areaCheckBean = new AreaCheckBean();
    }
}