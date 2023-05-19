package com.thisara.controller.dto.car;

import javax.validation.constraints.NotNull;

import com.thisara.validators.Size;


public class TyreDTO {

	@NotNull @Size(maxProperty = "car.tyre.number.length.max")
	private String number;

	@NotNull @Size(maxProperty = "car.tyre.year.length.max")
	private String year;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "TyreDTO [number=" + number + ", year=" + year + "]";
	}
}
