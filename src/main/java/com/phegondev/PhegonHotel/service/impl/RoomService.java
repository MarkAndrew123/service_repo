package com.phegondev.PhegonHotel.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.phegondev.PhegonHotel.dto.Response;
import com.phegondev.PhegonHotel.dto.RoomDTO;
import com.phegondev.PhegonHotel.entity.Room;
import com.phegondev.PhegonHotel.exception.OurException;
//import com.phegondev.PhegonHotel.repo.BookingRepository;
//import com.phegondev.PhegonHotel.repo.BookingRepository;
import com.phegondev.PhegonHotel.repo.RoomRepository;
import com.phegondev.PhegonHotel.service.AwsS3Service;
import com.phegondev.PhegonHotel.service.interfac.IRoomService;
import com.phegondev.PhegonHotel.utils.Utils;

@Service
public class RoomService implements IRoomService{

	
	@Autowired
	private RoomRepository roomRepository;
	
//	@Autowired
//	private BookingRepository bookingRepository;
	
	@Autowired
	private AwsS3Service awsS3Service;
	@Override
	public Response addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice, String roomDescription){
	Response response = new Response();
	
			try {
				String imageUrl = awsS3Service.saveImageToS3(photo);
				Room room = new Room();
				room.setRoomPhotoUrl(imageUrl);
				room.setRoomType(roomType);
				room.setRoomPrice(roomPrice);
				room.setRoomDescription(roomDescription);
				Room saveRoom =  roomRepository.save(room);
				
				RoomDTO roomDTO =  Utils.mapRoomEntitytoRoomDTO(saveRoom);
				response.setStatusCode(200);
				response.setMessage("Successful");
				response.setRoom(roomDTO);
				
				} catch (Exception e) {
				response.setStatusCode(500);
				response.setMessage("Error saving a room" + e.getMessage());
				
			}
		return response;
	}

	@Override
	public List<String> getAllRoomType() {
		
		return roomRepository.findDistinctRoomTypes();
	}

	@Override
	public Response getAllRooms() 	{
		
		Response response  =  new Response();
		 try {
			 List<Room> roomList = roomRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
			 List<RoomDTO> roomDTOList = Utils.mapRoomListEntityToRoomDTOs(roomList);
			 response.setStatusCode(200);
			 response.setMessage("Successful");
			 response.setRoomList(roomDTOList);
		} catch (Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error  getting all rooms " + e.getMessage());
			
		}
		return response;
	}

	@Override
	public Response deleteRoom(Long roomId) {
		Response response  =  new Response();
		 try {
			roomRepository.findById(roomId).orElseThrow(() -> new OurException("Room not Found"));
			roomRepository.deleteById(roomId);
			 response.setStatusCode(200);
			 response.setMessage("Successful");
		 }
			 catch (OurException  e) {
				 response.setStatusCode(404);
				 response.setMessage(e.getMessage());
				 
				 
		
			
			
		} catch (Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error  getting all rooms " + e.getMessage());
			
		}
		return response; 
	}

	 
@Override
	public Response updateRoom(Long roomId,String description, String roomType, BigDecimal roomPrice, MultipartFile photo) {
		Response response  =  new Response();
		 try {
			 String imageUrl = null;
			 if (photo != null && !photo.isEmpty()) {
				 imageUrl =  awsS3Service.saveImageToS3(photo);
			 }
			 Room room =  roomRepository.findById(roomId).orElseThrow(() -> new OurException("Room NOt Found"));
			 if (roomType != null)  room.setRoomType(roomType);
			 if (roomPrice != null)  room.setRoomPrice(roomPrice);
			 if (description != null)  room.setRoomDescription(description);
			 if (imageUrl != null)  room.setRoomPhotoUrl(imageUrl); 
			 
			 Room updatedRoom = roomRepository.save(room);
			 RoomDTO roomDTO = Utils.mapRoomEntitytoRoomDTO(updatedRoom);
			

			 
			 response.setStatusCode(200);
			 response.setMessage("Successful");
			 response.setRoom(roomDTO);
		 }
			 catch (OurException  e) {
				 response.setStatusCode(404);
				 response.setMessage(e.getMessage());
				 
				 
		
			
			
		} catch (Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error  getting all rooms " + e.getMessage());
			
		}
		return response;
	}
	@Override
	public Response getRoomById(Long roomId) {
		Response response  =  new Response();
		 try {
			 
			 
			 Room room =  roomRepository.findById(roomId).orElseThrow(() -> new OurException("Room NOt Found"));
			 RoomDTO roomDTO = Utils.mapRoomEntitytoRoomDTOPlusBookings(room);
			 roomRepository.deleteById(roomId);
			 response.setStatusCode(200);
			 response.setMessage("Successful");
			 response.setRoom(roomDTO);
		 }
			 catch (OurException  e) {
				 response.setStatusCode(404);
				 response.setMessage(e.getMessage());
				 
				 
		
			
			
		} catch (Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error  getting all rooms " + e.getMessage());
			
		}
		return response;
	}
		
	@Override
	public Response getAvailableRoomsByDateAndType(LocalDate checkInDate, LocalDate checkOutDate, String RoomType) {
		
		Response response = new Response();
 try {
			 
			 
			List<Room> availableRooms = roomRepository.findAvailableRoomsByDateAndTypes(checkInDate, checkOutDate, RoomType);
			List<RoomDTO> roomDTOList = Utils.mapRoomListEntityToRoomDTOs(availableRooms);
			response.setStatusCode(200);
			response.setMessage("Successfu");
			response.setRoomList(roomDTOList);
			
 } 
			catch (Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error  getting all rooms " + e.getMessage());
			
		}
		return response;
	}

	@Override
	public Response getAllAvailableRooms() {
		
		Response response = new Response();
		 try {
			 
			    List<Room> roomList = roomRepository.getAllAvailableRooms();
				List<RoomDTO> roomDTOList = Utils.mapRoomListEntityToRoomDTOs(roomList);
				response.setStatusCode(200);
				response.setMessage("Successfu");
				response.setRoomList(roomDTOList);
		 }
				
			
				catch (Exception e) {
				response.setStatusCode(500);
				response.setMessage("Error  getting all rooms " + e.getMessage());
				
			}
			return response;
		}

		
		
		
}

	
	
