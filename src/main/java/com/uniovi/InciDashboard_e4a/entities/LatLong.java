package com.uniovi.InciDashboard_e4a.entities;

import javax.persistence.Embeddable;

import utils.Assert;

@Embeddable
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
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LatLong other = (LatLong) obj;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Latitude='" + latitude + "'," + "Longitude='" + longitude + "'";
	}
}
