package com.phegondev.PhegonHotel.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.phegondev.PhegonHotel.dto.BookingDTO;
import com.phegondev.PhegonHotel.dto.Response;
import com.phegondev.PhegonHotel.entity.Booking;
import com.phegondev.PhegonHotel.entity.Room;
import com.phegondev.PhegonHotel.entity.User;
import com.phegondev.PhegonHotel.exception.OurException;
import com.phegondev.PhegonHotel.repo.BookingRepository;
import com.phegondev.PhegonHotel.repo.RoomRepository;
import com.phegondev.PhegonHotel.repo.UserRepository;
import com.phegondev.PhegonHotel.service.interfac.IBookingService;
//import com.phegondev.PhegonHotel.service.interfac.IRoomService;
//import com.phegondev.PhegonHotel.service.interfac.IRoomService;
import com.phegondev.PhegonHotel.utils.Utils;

@Service
public class BookingService implements IBookingService {
	
	
	@Autowired
	private BookingRepository bookingRepository;
	
//	@Autowired
//	private IRoomService roomService;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired	
	private RoomRepository roomRepository;

	@Override
		public Response saveBooking(Long roomId, Long userId, Booking bookingRequest) {
		Response response =  new Response();
		try {
			if (bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())) {
				throw new IllegalArgumentException("Check in date come before check out date");
				
			}
			
			Room room = roomRepository.findById(roomId).orElseThrow(() -> new  OurException("Room Not Found"));
			 User user = userRepository.findById(userId).orElseThrow(() -> new OurException("User Not Found"));
			 
			 List<Booking> existingBookings = room.getBookings();
			 if (!roomisAvailable(bookingRequest, existingBookings)) {
				 throw new OurException("Room not available for selected date range ");
				
			}
			 bookingRequest.setRoom(room);
			 bookingRequest.setUser(user);
			 String bookingConfirmationCodeString = Utils.generateRandomAlphanumeric(10);
			 bookingRequest.setBookingConfirmationCode(bookingConfirmationCodeString);
			 bookingRepository.save(bookingRequest);
			 response.setStatusCode(200);
			 response.setMessage("Successful");
			 response.setBookingConfirmationCode(bookingConfirmationCodeString);
		 
		}catch (OurException e) {
			response.setStatusCode(404);
			response.setMessage(e.getMessage());
		
		}
			
		 catch (Exception e) {
			response.setStatusCode(500);
			response.setMessage( "Error saving a booking" + e.getMessage() );
			 
		}
			return response;
		}

	private boolean roomisAvailable(Booking bookingRequest, List<Booking> existingBooking) {
	
           return existingBooking.stream()
		           .noneMatch(existingBookings ->
		              bookingRequest.getCheckInDate().equals(existingBookings.getCheckInDate())
		                   || bookingRequest.getCheckOutDate().isBefore(existingBookings.getCheckOutDate())
		                   || (bookingRequest.getCheckInDate().isAfter(existingBookings.getCheckInDate())
		                   && bookingRequest.getCheckInDate().isBefore(existingBookings.getCheckOutDate()))
		                   || (bookingRequest.getCheckInDate().isBefore(existingBookings.getCheckInDate())
		                
		                   &&  bookingRequest.getCheckInDate().equals(existingBookings.getCheckOutDate()))
		                   ||  (bookingRequest.getCheckInDate().isBefore(existingBookings.getCheckInDate())
		                	
		                   &&  bookingRequest.getCheckInDate().isAfter(existingBookings.getCheckOutDate()))
		                   
		                   ||  (bookingRequest.getCheckInDate().equals(existingBookings.getCheckOutDate())
		                   &&   bookingRequest.getCheckInDate().equals(existingBookings.getCheckInDate()))
		                   
		                   ||  (bookingRequest.getCheckInDate().equals(existingBookings.getCheckOutDate())
		                   &&   bookingRequest.getCheckOutDate().equals(bookingRequest.getCheckInDate()))
		                   );
		                		 
		                		   
		                		  
	}

	@Override
	public Response findBookingByConfirmationCode(String confirmation) {
		Response response = new Response();	
		try {
		Booking booking = bookingRepository.findByBookingConfirmationCode(confirmation).orElseThrow(()-> new OurException("Booking confirmation not found"));
		BookingDTO bookingDTO = Utils.mapBookingEntityToBookingDTOPlusBookedRooms(booking, true);
	    response.setStatusCode(200);
	    response.setMessage("Successful");
	    response.setBooking(bookingDTO);
		}
		catch  (OurException e) {
		 response.setStatusCode(404);
		 response.setMessage(e.getMessage());
		 
		} catch (Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error cancelling a booking" + e.getMessage());
		}
		
		
		
		return response;
	}

	@Override
	public Response getAllBookings() {
		Response response = new Response();	
		try {
			List<Booking> bookingList = bookingRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
			List<BookingDTO> bookingDTOList = Utils.mapBookingListEntityToBookingDTOs(bookingList);
			response.setStatusCode(200);
			response.setMessage("Successful");
			response.setBookingList(bookingDTOList);
 			
			
			
		} catch (Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error cancelling a booking" + e.getMessage());
		}
		
		
	
		return response;
	}

	@Override
	public Response cancelBooking(Long bookingId) {
		Response response = new Response();
		try {
			bookingRepository.findById(bookingId).orElseThrow(()-> new OurException("Booking Does not Exist"));
			bookingRepository.deleteById(bookingId);
			response.setStatusCode(200);
			response.setMessage("Successful");
			
			
		} catch (OurException e) {
			response.setStatusCode(404);
			response.setMessage(e.getMessage());
		}
		catch (Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error cancelling a booking" + e.getMessage());
		}
		
		return response;
	}

}
