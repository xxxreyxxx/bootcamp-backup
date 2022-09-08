package com.parking.procces;

import java.math.BigDecimal;
import java.util.Date;

import com.parking.entity.transaction.TransactionHeader;

public class ProccesGet {
	public static Double getDistance(Double lat1, Double lon1, Double lat2, Double lon2) {
		final int R = 6371; // Radious of the earth
		Double latDistance = toRad(lat2 - lat1);
		Double lonDistance = toRad(lon2 - lon1);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return R * c;
	}

	private static Double toRad(Double value) {
		return value * Math.PI / 180;
	}

	public Integer dateCalculate(Date date) {
		Long diff = new Date().getTime() - date.getTime();
		Long diffMinutes = diff / (60 * 1000);
		Double diffUp = Double.valueOf(diffMinutes.toString()) / 60;
		return (int) Math.ceil(diffUp);
	}

	public BigDecimal price(Integer diff, TransactionHeader header) {
		BigDecimal priceHourMin;
		BigDecimal priceHourMax;
		BigDecimal newDiff;

		Long slotType = header.getSlot().getSlotType();

		if (diff > 2) {
			if (slotType == 1) {
				priceHourMin = BigDecimal
						.valueOf(header.getSlot().getFloor().getBuilding().getMinPrice().doubleValue() * 2);
				priceHourMax = BigDecimal
						.valueOf(header.getSlot().getFloor().getBuilding().getMaxPrice().doubleValue() * 2);
				newDiff = BigDecimal.valueOf((diff - 1) * priceHourMin.doubleValue());
			} else {
				priceHourMin = header.getSlot().getFloor().getBuilding().getMinPrice();
				priceHourMax = header.getSlot().getFloor().getBuilding().getMaxPrice();
				newDiff = BigDecimal.valueOf((diff - 1) * priceHourMin.doubleValue());
			}

			if (newDiff.doubleValue() > priceHourMax.doubleValue()) {
				return priceHourMax;
			} else {
				return newDiff;
			}
		} else {
			if (slotType == 1) {
				return BigDecimal.valueOf(header.getSlot().getFloor().getBuilding().getMinPrice().doubleValue() * 2);
			} else {
				return header.getSlot().getFloor().getBuilding().getMinPrice();
			}
		}
	}
}
