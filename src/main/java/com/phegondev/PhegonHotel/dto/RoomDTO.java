package com.phegondev.PhegonHotel.dto;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;


import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoomDTO {
	
	private Long id;
	private String roomType;
	private BigDecimal roomPrice;
	private String photoUrl;
	private String roomDescription;
	private List<BookingDTO> bookings;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public BigDecimal getRoomPrice() {
		return roomPrice;
	}
	public void setRoomPrice(BigDecimal roomPrice) {
		this.roomPrice = roomPrice;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public String getRoomDescription() {
		return roomDescription;
	}
	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}
	public List<BookingDTO> getBookings() {
		return bookings;
	}
	public void setBookings(List<BookingDTO> bookings) {
		this.bookings = bookings;
	}
	@Override
	public String toString() {
		return "RoomDTO [id=" + id + ", roomType=" + roomType + ", roomPrice=" + roomPrice + ", photoUrl=" + photoUrl
				+ ", roomDescription=" + roomDescription + ", bookings=" + bookings + "]";
	} 
	

}
