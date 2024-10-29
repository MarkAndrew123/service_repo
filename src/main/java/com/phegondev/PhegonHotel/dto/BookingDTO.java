package com.phegondev.PhegonHotel.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookingDTO {
	private long id;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private int numOfAdults;
	private int numOfChildren;
	private int totalNumOfGuests;
	private String bookingConfirmationCode;
	private UserDTO user;
	private RoomDTO room;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDate getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}
	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public int getNumOfAdults() {
		return numOfAdults;
	}
	public void setNumOfAdults(int numOfAdults) {
		this.numOfAdults = numOfAdults;
	}
	public int getNumOfChildren() {
		return numOfChildren;
	}
	public void setNumOfChildren(int numOfChildren) {
		this.numOfChildren = numOfChildren;
	}
	public int getTotalNumOfGuests() {
		return totalNumOfGuests;
	}
	public void setTotalNumOfGuests(int totalNumOfGuests) {
		this.totalNumOfGuests = totalNumOfGuests;
	}
	public String getBookingConfirmationCode() {
		return bookingConfirmationCode;
	}
	public void setBookingConfirmationCode(String bookingConfirmationCode) {
		this.bookingConfirmationCode = bookingConfirmationCode;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public RoomDTO getRoom() {
		return room;
	}
	public void setRoom(RoomDTO room) {
		this.room = room;
	}
	@Override
	public String toString() {
		return "BookingDTO [id=" + id + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate
				+ ", numOfAdults=" + numOfAdults + ", numOfChildren=" + numOfChildren + ", totalNumOfGuests="
				+ totalNumOfGuests + ", bookingConfirmationCode=" + bookingConfirmationCode + ", user=" + user
				+ ", room=" + room + "]";
	}

}
