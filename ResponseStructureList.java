package com.project.Hotel.Reservation.System.util;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class ResponseStructureList<T> {
	private int statusCode;
	private String message;
	private List<T> dataList;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	

}
