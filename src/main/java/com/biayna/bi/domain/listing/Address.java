package com.biayna.bi.domain.listing;

import java.util.Arrays;

/**
 * Address - Address entity object, representing the address of the listing.<br><br>
 * 
 * @author Fred Agourian
 * 
 */
public class Address{
	private String[] addressLine;
	private String city;
	private String county;
	private String state;
	private String zipcode;
	private String mlsArea;
	private String mlsAreaCode;
	
	public Address(String[] addressLine, String city, String county, String state, String zipcode, String mlsArea,
			String mlsAreaCode) {
		super();
		this.addressLine = addressLine;
		this.city = city;
		this.county = county;
		this.state = state;
		this.zipcode = zipcode;
		this.mlsArea = mlsArea;
		this.mlsAreaCode = mlsAreaCode;
	}
	
	public String[] getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String[] addressLine) {
		this.addressLine = addressLine;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getMlsArea() {
		return mlsArea;
	}
	public void setMlsArea(String mlsArea) {
		this.mlsArea = mlsArea;
	}
	public String getMlsAreaCode() {
		return mlsAreaCode;
	}
	public void setMlsAreaCode(String mlsAreaCode) {
		this.mlsAreaCode = mlsAreaCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(addressLine);
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((county == null) ? 0 : county.hashCode());
		result = prime * result + ((mlsArea == null) ? 0 : mlsArea.hashCode());
		result = prime * result + ((mlsAreaCode == null) ? 0 : mlsAreaCode.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
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
		Address other = (Address) obj;
		if (!Arrays.equals(addressLine, other.addressLine))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (county == null) {
			if (other.county != null)
				return false;
		} else if (!county.equals(other.county))
			return false;
		if (mlsArea == null) {
			if (other.mlsArea != null)
				return false;
		} else if (!mlsArea.equals(other.mlsArea))
			return false;
		if (mlsAreaCode == null) {
			if (other.mlsAreaCode != null)
				return false;
		} else if (!mlsAreaCode.equals(other.mlsAreaCode))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (zipcode == null) {
			if (other.zipcode != null)
				return false;
		} else if (!zipcode.equals(other.zipcode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [addressLine=");
		builder.append(Arrays.toString(addressLine));
		builder.append(", city=");
		builder.append(city);
		builder.append(", county=");
		builder.append(county);
		builder.append(", state=");
		builder.append(state);
		builder.append(", zipcode=");
		builder.append(zipcode);
		builder.append(", mlsArea=");
		builder.append(mlsArea);
		builder.append(", mlsAreaCode=");
		builder.append(mlsAreaCode);
		builder.append("]");
		return builder.toString();
	}
	
		
	
}