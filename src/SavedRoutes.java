/**
 * Represents a saved route with a start location, end location, route ID, saved route ID,
 * account ID, and saved date.
 */
public class SavedRoutes {
    private String startLoc;
    private String endLoc;
    private String routesID;
    private String savedRouteID;
    private String accoID;
    private String savedAt;

/**
 * Constructs a new SavedRoutes instance with the specified details.
 *
 * @param startLoc the address of the starting location
 * @param endLoc the address of the ending location
 * @param routesID the ID associated with the route
 * @param savedRouteID the ID associated with the saved route
 * @param accoID the ID associated with the account and route
 * @param savedAt the date that the route was saved on
 */

 public SavedRoutes(final String startLoc, final String endLoc, final String routesID, final String savedRouteID,
                   final String accoID, final String savedAt) {
     this.startLoc = startLoc;
     this.endLoc = endLoc;
     this.routesID = routesID;
     this.savedRouteID = savedRouteID;
     this.accoID = accoID;
     this.savedAt = savedAt;
 }

    public String getStartLoc() {return startLoc;}

    public String getEndLoc() {return endLoc;}

    public String getRoutesID() {return routesID;}

    public String getSavedRouteID() {return savedRouteID;}

    public String getAccoID() {return accoID;}

    public String getSavedAt() {return savedAt;}
}
