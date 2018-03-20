package com.uniovi.InciDashboard_e4a.entities.location;

import utils.Assert;

public class LatLong {

	public String latitude;
	public String longitude;

	public LatLong() {
	}

	public LatLong(String lat, String lng) {
		Assert.isNull(lat);
		Assert.isNull(lng);
		this.latitude = lat;
		this.longitude = lng;
	}

	@Override
	public String toString() {
		return "Location{Latitude='" + latitude + "'," + "Longitude='" + longitude + "'}";
	}
}
