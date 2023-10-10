package isp.lab8.airways;
import lombok.*;

/**
 * Example waypoint class which can be extended to be used in implementation of the exercise. Add constructor, getters, setters, etc.
 */
@NoArgsConstructor
@AllArgsConstructor
public class Waypoint {
    @Setter @Getter private int index;
    @Setter @Getter private String name;
    @Setter @Getter private double latitude;
    @Setter @Getter private double longitude;
    @Setter @Getter private int altitude;

    public double calculateDistance(Waypoint x) {
        int earthRadius = 6371; // Radius of the Earth in kilometers
        double dLat = Math.toRadians(x.latitude - this.latitude);
        double dLon = Math.toRadians(x.longitude - this.longitude);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(x.latitude)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;
        return distance;
    }

    public String toString()
    {
        return index+"_"+name+":"+latitude+"° N,"+longitude+"° E,"+altitude+"meters altitude";
    }

}
