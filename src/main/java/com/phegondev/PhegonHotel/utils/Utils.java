package com.phegondev.PhegonHotel.utils;


import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import java.util.stream.Collectors;

import com.phegondev.PhegonHotel.dto.BookingDTO;
import com.phegondev.PhegonHotel.dto.RoomDTO;
import com.phegondev.PhegonHotel.dto.UserDTO;
import com.phegondev.PhegonHotel.entity.Booking;
import com.phegondev.PhegonHotel.entity.Room;
import com.phegondev.PhegonHotel.entity.User;

public class Utils {
private static final String ALPHANUMERIC_STRING = "BCDEFGHIJKLMNOPQRSTUVWXYZ";

private static final SecureRandom secureRandom = new SecureRandom();




public static String generateRandomAlphanumeric(int length) {
	
	StringBuilder stringBuilder = new StringBuilder();
	for(int i = 0; i < length; i++) {
		int randomIndex  = secureRandom.nextInt(ALPHANUMERIC_STRING.length());
		char randomChar = ALPHANUMERIC_STRING.charAt(randomIndex);
		stringBuilder.append(randomChar);
		
	}
	return stringBuilder.toString();
}

public static  UserDTO mapUserEntitytoUserDTO(User user) {
	
	UserDTO userDTO = new UserDTO();
	
	userDTO.setId(user.getId());
	userDTO.setName(user.getName());
	userDTO.setEmail(user.getEmail());
	userDTO.setPhoneNumber(user.getPhoneNumber());
	userDTO.setRole(user.getRole());
	return userDTO;

    }
public static RoomDTO mapRoomEntitytoRoomDTO(Room room) {
	
	RoomDTO roomDTO = new RoomDTO();
	
	roomDTO.setId(room.getId());
	roomDTO.setRoomType(room.getRoomType());
	roomDTO.setRoomPrice(room.getRoomPrice());
	roomDTO.setPhotoUrl(room.getRoomPhotoUrl());
	roomDTO.setRoomDescription(room.getRoomDescription());
	return roomDTO;

    }
public static BookingDTO mapBookingsEntityToBookingDTO(Booking booking) {
	BookingDTO bookingDTO = new BookingDTO();
		
	bookingDTO.setId(booking.getId());
	bookingDTO.setCheckInDate(booking.getCheckInDate());
	bookingDTO.setCheckOutDate(booking.getCheckOutDate());
	bookingDTO.setNumOfAdults(booking.getNumOfAdults());
	bookingDTO.setNumOfChildren(booking.getNumOfChildren());
	bookingDTO.setTotalNumOfGuests(booking.getTotalNumOfGuests());
	bookingDTO.setBookingConfirmationCode(booking.getBookingConfirmationCode());
	
	return bookingDTO;
	
}



public static RoomDTO mapRoomEntitytoRoomDTOPlusBookings(Room room) {
	
	RoomDTO roomDTO = new RoomDTO();
	
	roomDTO.setId(room.getId());
	roomDTO.setRoomType(room.getRoomType());
	roomDTO.setRoomPrice(room.getRoomPrice());
	roomDTO.setPhotoUrl(room.getRoomPhotoUrl());
//	roomDTO.setRoomDescription(room.getRoomDescription());
	
	if (room.getBookings() != null) {
		
		roomDTO.setBookings(room.getBookings().stream().map(Utils::mapBookingsEntityToBookingDTO).collect(Collectors.toList()));
	}
	
	
	return roomDTO;

    }

 



	public static UserDTO mapUSerEntitytoUserDTOPlusUserBookingsAndRoom(User user) {
		
		UserDTO userDTO = new UserDTO();
		
		userDTO.setId(user.getId());
		userDTO.setName(user.getName());
		userDTO.setEmail(user.getEmail());
		userDTO.setPhoneNumber(user.getPhoneNumber());
		userDTO.setRole(user.getRole());
		
		
		 userDTO.setBookings(user.getBookings().isEmpty() 
			        ? new ArrayList<>() 
			        : user.getBookings().stream()
			              .map(booking -> mapBookingEntityToBookingDTOPlusBookedRooms(booking, false))
			              .collect(Collectors.toList()));
		
		
		return userDTO;
		
		
	}

public static BookingDTO mapBookingEntityToBookingDTOPlusBookedRooms(Booking booking, boolean mapUser) {
	
	BookingDTO bookingDTO = new BookingDTO();
	
	bookingDTO.setId(booking.getId());
	bookingDTO.setCheckInDate(booking.getCheckInDate());
	bookingDTO.setCheckOutDate(booking.getCheckOutDate());
	bookingDTO.setNumOfAdults(booking.getNumOfAdults());
	bookingDTO.setNumOfChildren(booking.getNumOfChildren());
	bookingDTO.setTotalNumOfGuests(booking.getTotalNumOfGuests());
	bookingDTO.setBookingConfirmationCode(booking.getBookingConfirmationCode());
	
	if (mapUser) {
		bookingDTO.setUser(Utils.mapUserEntitytoUserDTO(booking.getUser()));
	}
	if(booking.getRoom()  != null) {
		
		RoomDTO roomDTO = new RoomDTO();
		
		roomDTO.setId(booking.getRoom().getId());
		roomDTO.setRoomType(booking.getRoom().getRoomType());
		roomDTO.setRoomPrice(booking.getRoom().getRoomPrice());
		roomDTO.setPhotoUrl(booking.getRoom().getRoomPhotoUrl());
		roomDTO.setRoomDescription(booking.getRoom().getRoomDescription());
		bookingDTO.setRoom(roomDTO);
	}
	
	
	return bookingDTO;
	
}
public static List<UserDTO> mapUserListEntityToUserListDTO(List<User> userList) {
	return userList.stream().map(Utils::mapUserEntitytoUserDTO).collect(Collectors.toList());
}
public static List<RoomDTO> mapRoomListEntityToRoomDTOs(List<Room> roomList) {
	return roomList.stream().map(Utils::mapRoomEntitytoRoomDTO).collect(Collectors.toList());

}
public static List<BookingDTO> mapBookingListEntityToBookingDTOs(List<Booking> bookingList) {
	return bookingList.stream().map(Utils::mapBookingsEntityToBookingDTO).collect(Collectors.toList());
}
    }

