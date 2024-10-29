package com.phegondev.PhegonHotel.entity;

import java.time.LocalDate;



import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name= "bookings")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull(message = "check in date is required")
	private LocalDate checkInDate;
	@Future(message = "check out date must be in the future")
	private LocalDate checkOutDate;
	 
	@Min(value = 1, message = "Number of adults should not be less than 1")
	private int numOfAdults;
	@Min(value = 0, message = "Number of adults should not be less than 0")
	private int numOfChildren;
	
	private int totalNumOfGuests;
	private String bookingConfirmationCode;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name= "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name= "user-ids")
	private Room room;
 
	public void calculateTotalNumberOfGuest() {
		this.totalNumOfGuests =  this.numOfAdults + this.numOfChildren;
	}

	

	public void setNumOfAdults(int numOfAdults) {
		this.numOfAdults = numOfAdults;
		calculateTotalNumberOfGuest();
	}

	

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



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Room getRoom() {
		return room;
	}



	public void setRoom(Room room) {
		this.room = room;
	}



	public int getNumOfAdults() {
		return numOfAdults;
	}



	public int getNumOfChildren() {
		return numOfChildren;
	}



	public void setNumOfChildren(int numOfChildren) {
		this.numOfChildren = numOfChildren;
		calculateTotalNumberOfGuest();
	}



	@Override
	public String toString() {
		return "Booking [id=" + id + 
				", checkInDate=" +checkInDate +
				", checkOutDate=" + checkOutDate+ 
				", numOfAdults=" + numOfAdults + 
				", numOfChildren=" + numOfChildren + 
				", totalNumOfGuests="+ totalNumOfGuests + 
				", bookingConfirmationCode=" + bookingConfirmationCode + 
				"]";
	}
	
	
	
}
