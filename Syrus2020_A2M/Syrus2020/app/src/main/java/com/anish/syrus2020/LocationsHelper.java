package com.anish.syrus2020;

import android.location.Location;

public class LocationsHelper {

    Location location;

    public LocationsHelper(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
