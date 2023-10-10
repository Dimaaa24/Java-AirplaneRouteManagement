package isp.lab8.airways;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AirwaysTest
{
    @Test
    public void testDistance()
    {
        Waypoint x=new Waypoint(1,"awda",10,20,123);
        Waypoint y=new Waypoint(2,"awdaw",100,213.423,123);
        double dist=x.calculateDistance(y);
        assertEquals(dist,7815.288731237992,0);
    }

    @Test
    public void testDistanceRoute()
    {
        Waypoint x=new Waypoint(1,"awda",10,20,123);
        Waypoint y=new Waypoint(2,"awdaw",100,213.423,123);
        Waypoint z=new Waypoint(3,"werw",234.5,123.4,100);
        Route w=new Route("CJ-B");
        w.addWaypoint(x);
        w.addWaypoint(y);
        w.addWaypoint(z);
        double dist=w.routeDistance();
        assertEquals(dist,23749.650810049283,0);
    }

}
