package com.biayna.bi.domain.listing;

import java.time.LocalDate;

/**
 * PropertyDetails - Property details entity object, representing all possible properties of the listing.<br><br>
 * 
 * @author Fred Agourian
 * 
 */

public class PropertyDetails {
	
	private int lotSize;
	private int structureSize;
	private float structureCoveragePercentage; //structureCoveragePercentage = structureSize / lotSize X 100%=00.0%
	private HouseTypes type;
	private LocalDate yearBuilt;
	private LocalDate soldDate;
	private int soldPrice;
	private int salePrice;
	private float pricePerSquareFoot;
	
	public PropertyDetails(int lotSize, int structureSize, float structureCoveragePercentage, HouseTypes type,
			LocalDate yearBuilt, LocalDate soldDate, int soldPrice, int salePrice, float pricePerSquareFoot) {
		super();
		this.lotSize = lotSize;
		this.structureSize = structureSize;
		this.structureCoveragePercentage = structureCoveragePercentage;
		this.type = type;
		this.yearBuilt = yearBuilt;
		this.soldDate = soldDate;
		this.soldPrice = soldPrice;
		this.salePrice = salePrice;
		this.pricePerSquareFoot = pricePerSquareFoot;
	}

	public int getLotSize() {
		return lotSize;
	}

	public void setLotSize(int lotSize) {
		this.lotSize = lotSize;
	}

	public int getStructureSize() {
		return structureSize;
	}

	public void setStructureSize(int structureSize) {
		this.structureSize = structureSize;
	}

	public float getStructureCoveragePercentage() {
		return structureCoveragePercentage;
	}

	public void setStructureCoveragePercentage(float structureCoveragePercentage) {
		this.structureCoveragePercentage = structureCoveragePercentage;
	}

	public HouseTypes getType() {
		return type;
	}

	public void setType(HouseTypes type) {
		this.type = type;
	}

	public LocalDate getYearBuilt() {
		return yearBuilt;
	}

	public void setYearBuilt(LocalDate yearBuilt) {
		this.yearBuilt = yearBuilt;
	}

	public LocalDate getSoldDate() {
		return soldDate;
	}

	public void setSoldDate(LocalDate soldDate) {
		this.soldDate = soldDate;
	}

	public int getSoldPrice() {
		return soldPrice;
	}

	public void setSoldPrice(int soldPrice) {
		this.soldPrice = soldPrice;
	}

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public float getPricePerSquareFoot() {
		return pricePerSquareFoot;
	}

	public void setPricePerSquareFoot(float pricePerSquareFoot) {
		this.pricePerSquareFoot = pricePerSquareFoot;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lotSize;
		result = prime * result + Float.floatToIntBits(pricePerSquareFoot);
		result = prime * result + salePrice;
		result = prime * result + ((soldDate == null) ? 0 : soldDate.hashCode());
		result = prime * result + soldPrice;
		result = prime * result + Float.floatToIntBits(structureCoveragePercentage);
		result = prime * result + structureSize;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((yearBuilt == null) ? 0 : yearBuilt.hashCode());
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
		PropertyDetails other = (PropertyDetails) obj;
		if (lotSize != other.lotSize)
			return false;
		if (Float.floatToIntBits(pricePerSquareFoot) != Float.floatToIntBits(other.pricePerSquareFoot))
			return false;
		if (salePrice != other.salePrice)
			return false;
		if (soldDate == null) {
			if (other.soldDate != null)
				return false;
		} else if (!soldDate.equals(other.soldDate))
			return false;
		if (soldPrice != other.soldPrice)
			return false;
		if (Float.floatToIntBits(structureCoveragePercentage) != Float
				.floatToIntBits(other.structureCoveragePercentage))
			return false;
		if (structureSize != other.structureSize)
			return false;
		if (type != other.type)
			return false;
		if (yearBuilt == null) {
			if (other.yearBuilt != null)
				return false;
		} else if (!yearBuilt.equals(other.yearBuilt))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PropertyDetails [lotSize=");
		builder.append(lotSize);
		builder.append(", structureSize=");
		builder.append(structureSize);
		builder.append(", structureCoveragePercentage=");
		builder.append(structureCoveragePercentage);
		builder.append(", type=");
		builder.append(type);
		builder.append(", yearBuilt=");
		builder.append(yearBuilt);
		builder.append(", soldDate=");
		builder.append(soldDate);
		builder.append(", soldPrice=");
		builder.append(soldPrice);
		builder.append(", salePrice=");
		builder.append(salePrice);
		builder.append(", pricePerSquareFoot=");
		builder.append(pricePerSquareFoot);
		builder.append("]");
		return builder.toString();
	}
	
}
