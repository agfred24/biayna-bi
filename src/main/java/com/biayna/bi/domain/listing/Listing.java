package com.biayna.bi.domain.listing;

import java.time.LocalDate;

/**
 * Listing - Listing entity object, representing the properties of the listing.<br><br>
 * 
 * @author Fred Agourian
 * 
 */
public class Listing {
	private Address address;
	private PropertyDetails details;
	private boolean isActive;
	private LocalDate listingDate;
	
	public Listing(Address address, PropertyDetails details, boolean isActive, LocalDate listingDate) {
		super();
		this.address = address;
		this.details = details;
		this.isActive = isActive;
		this.listingDate = listingDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public PropertyDetails getDetails() {
		return details;
	}

	public void setDetails(PropertyDetails details) {
		this.details = details;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDate getListingDate() {
		return listingDate;
	}

	public void setListingDate(LocalDate listingDate) {
		this.listingDate = listingDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + ((listingDate == null) ? 0 : listingDate.hashCode());
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
		Listing other = (Listing) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (isActive != other.isActive)
			return false;
		if (listingDate == null) {
			if (other.listingDate != null)
				return false;
		} else if (!listingDate.equals(other.listingDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Listing [address=");
		builder.append(address);
		builder.append(", details=");
		builder.append(details);
		builder.append(", isActive=");
		builder.append(isActive);
		builder.append(", listingDate=");
		builder.append(listingDate);
		builder.append("]");
		return builder.toString();
	}
	
}
