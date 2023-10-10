package isp.lab8.airways;
import lombok.*;
import java.util.ArrayList;
import java.util.List;


public class Route
{
    @Getter @Setter private String name;
    private List<Waypoint> waypoints;

    Route(String name)
    {
        this.name=name;
        waypoints=new ArrayList<Waypoint>();
    }

    public void addWaypoint(Waypoint x)
    {
        waypoints.add(x);
    }

    public double routeDistance()
    {
        double distance=0;
        int index=0;
        int index2=waypoints.size();
        for(Waypoint i:waypoints)
        {
            distance+= i.calculateDistance(waypoints.get(index+1));
            index++;
            if(index==index2-1)
                break;
        }
        return distance;
    }

}
