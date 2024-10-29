package com.phegondev.PhegonHotel.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.phegondev.PhegonHotel.dto.Response;

//import com.phegondev.PhegonHotel.service.interfac.IBookingService;
import com.phegondev.PhegonHotel.service.interfac.IRoomService;


@RestController
@RequestMapping("/rooms")
public class RoomController {


	@Autowired
	private IRoomService iRoomService;
	
	
//	@Autowired
//	private IBookingService iBookingService;
	
	@PostMapping("/add")
	@PreAuthorize("hasAuthority('ADMIN' )")
	public ResponseEntity<Response> addNewRoom(
			@RequestParam(value = "photo" , required = false)MultipartFile photo,
			@RequestParam(value = "roomType" , required = false)String roomType,
			@RequestParam(value = "roomPrice" , required = false)BigDecimal roomPrice,
			@RequestParam(value = "roomDescription" , required = false)String roomDescription){
		
		
		
		
		if (photo == null || photo.isEmpty() ||	roomType == null || roomType.isBlank() ||roomPrice == null || roomType.isBlank()) {
			Response response = new Response();
			response.setStatusCode(400);
			response.setMessage("Please provide values for all fields(photo, roomType, roomPrice)");
			return ResponseEntity.status(response.getStatusCode()).body(response);
		}
		
		Response response  = iRoomService.addNewRoom(photo, roomType, roomPrice, roomDescription);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	@GetMapping("/all")
	public ResponseEntity<Response> getAllRooms(){
		Response response = iRoomService.getAllRooms();
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
		
	@GetMapping("/types")
	public List<String> getRoomTypes(){
		
		return iRoomService.getAllRoomType();
	}
	@GetMapping("/room-by-id/{roomId}")
	
	public ResponseEntity<Response> getRoomByid(@PathVariable Long roomId) {
		Response response = iRoomService.getRoomById(roomId);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
@GetMapping("/all-available-rooms")
//@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	
	public ResponseEntity<Response> getAvailableRooms() {
		Response response = iRoomService.getAllAvailableRooms();
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
@GetMapping("/available-rooms-by-date-and-type")
public ResponseEntity<Response> getAvailableRoomsByDateAndType(
		@RequestParam(required = false) @DateTimeFormat(iso = ISO.DATE) LocalDate checkInDate,
		@RequestParam( required = false)@DateTimeFormat(iso = ISO.DATE) LocalDate checkOutDate,
		@RequestParam( required = false)String roomType){
	if (checkInDate == null || 	roomType == null || roomType.isBlank() ||checkOutDate == null ) {
		Response response = new Response();
		response.setStatusCode(400);
		response.setMessage("Please provide values for all fields(check in date, roomType, check out date)");
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
	
	Response response  = iRoomService.getAvailableRoomsByDateAndType(checkInDate,checkOutDate,roomType);
	return ResponseEntity.status(response.getStatusCode()).body(response);
}
@PutMapping("/update/{roomId}")
@PreAuthorize("hasAuthority('ADMIN')")
public ResponseEntity<Response> updateRoom(@PathVariable long roomId,
		                                   @RequestParam(value = "photo", required = false) MultipartFile photo,
		                                   @RequestParam(value = "roomType", required = false) String roomType,
		                                   @RequestParam(value = "roomPrice", required = false) BigDecimal roomPrice,
		                                   @RequestParam(value = "roomDescription", required = false) String  roomDescription){
	Response response = iRoomService.updateRoom(roomId, roomDescription, roomType, roomPrice, photo);
	return ResponseEntity.status(response.getStatusCode()).body(response);
}

@DeleteMapping("/delete/{roomId}")
@PreAuthorize("hasAuthority('ADMIN')")
public ResponseEntity<Response> deleteRoom(@PathVariable Long roomId){

Response response = iRoomService.deleteRoom(roomId);
return ResponseEntity.status(response.getStatusCode()).body(response);
}
 
}