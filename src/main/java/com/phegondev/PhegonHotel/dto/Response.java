package com.phegondev.PhegonHotel.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Response {
    private int statusCode;
	private String message;
	private String Token;
	private String Role;
	private String expiratoniTime;
	private String bookingConfirmationCode;
	private UserDTO  user;
	private RoomDTO room;
	private BookingDTO booking;
    private List<UserDTO> userlist;
    private List<RoomDTO> roomList;
    private List<BookingDTO> bookingList;
    
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
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public String getExpiratoniTime() {
		return expiratoniTime;
	}
	public void setExpiratoniTime(String expiratoniTime) {
		this.expiratoniTime = expiratoniTime;
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
	public BookingDTO getBooking() {
		return booking;
	}
	public void setBooking(BookingDTO booking) {
		this.booking = booking;
	}
	public List<UserDTO> getUserlist() {
		return userlist;
	}
	public void setUserlist(List<UserDTO> userlist) {
		this.userlist = userlist;
	}
	public List<RoomDTO> getRoomList() {
		return roomList;
	}
	public void setRoomList(List<RoomDTO> roomList) {
		this.roomList = roomList;
	}
	public List<BookingDTO> getBookingList() {
		return bookingList;
	}
	public void setBookingList(List<BookingDTO> bookingList) {
		this.bookingList = bookingList;
	}
	@Override
	public String toString() {
		return "Response [statusCode=" + statusCode + ", message=" + message + ", Token=" + Token + ", Role=" + Role
				+ ", expiratoniTime=" + expiratoniTime + ", bookingConfirmationCode=" + bookingConfirmationCode
				+ ", user=" + user + ", room=" + room + ", booking=" + booking + ", userlist=" + userlist
				+ ", roomList=" + roomList + ", bookingList=" + bookingList + "]";
	}
	
}
