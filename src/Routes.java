import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Represents a route with a start location, end location, route ID, acc ID, location, and
 * distance preference.
 */
public class Routes{
    private String startLocation;
    private String endLocation;
    private String routeID;
    private String accID;
    private String location;
    private String distancePreference;
    private Double distance;

/**
 * Constructs a new Routes instance with the specified details.
 *
 * @param startLocation the address of the starting location
 * @param endLocation the address of the ending location
 * @param routeID the ID associated with the route
 * @param accID the ID associated with the account and route
 * @param location the general location associated with the route
 * @param distancePreference the preferred mileage of the route
 */

 public Routes(final String startLocation, final String endLocation, final String routeID,
               final String accID, final String location, final String distancePreference){
     this.startLocation = startLocation;
     this.endLocation = endLocation;
     this.routeID = routeID;
     this.accID = accID;
     this.location = location;
     this.distancePreference = distancePreference;
 }

    public String getStartLocation() {return startLocation;}

    public String getEndLocation() {return endLocation;}

    public String getRouteID() {return routeID;}

    public String getAccID() {return accID;}

    public String getLocation() {return location;}

    public String getDistancePreference() {return distancePreference;}

    public double getDistance() {
        return distance;
    }
}
