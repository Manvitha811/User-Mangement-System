package com.user_management.controller;

import com.user_management.dto.*;
import com.user_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class UserRestController {
    @Autowired
    private UserService userService;
    @GetMapping("/countries")
    public ResponseEntity<ApiResponse<List<CountryDto>>> getCountries() {

        List<CountryDto> countries = userService.getCountries();

        ApiResponse<List<CountryDto>> response = new ApiResponse<>();

        response.setStatus(200);
        response.setMsg("Fetched Countries");
        response.setPayload(countries);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/states/{countryId}")
    public ResponseEntity<ApiResponse<List<StateDto>>> getStates(@PathVariable Integer countryId) {

        List<StateDto> states = userService.getStates(countryId);

        ApiResponse<List<StateDto>> response = new ApiResponse<>();

        response.setStatus(200);
        response.setMsg("Fetched states");
        response.setPayload(states);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/cities/{stateId}")
    public ResponseEntity<ApiResponse<List<CityDto>>> getCities(@PathVariable Integer stateId) {

        List<CityDto> cities = userService.getCities(stateId);

        ApiResponse<List<CityDto>> response = new ApiResponse<>();

        response.setStatus(200);
        response.setMsg("Fetched cities");
        response.setPayload(cities);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/user/{email}")
    public ResponseEntity<ApiResponse<UserDto>> getUser(@PathVariable String email) {

        UserDto userByEmail = userService.getUserByEmail(email);

        ApiResponse<UserDto> response = new ApiResponse<>();

        response.setMsg("User fetched");
        response.setStatus(200);
        response.setPayload(userByEmail);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/user")
    public ResponseEntity<ApiResponse<UserDto>> saveUser(@RequestBody UserDto userDto) {

        UserDto registerUser = userService.registerUser(userDto);

        ApiResponse<UserDto> response = new ApiResponse<>();

        response.setMsg("User Created");
        response.setStatus(201);
        response.setPayload(registerUser);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserDto>> login(@RequestBody UserDto userDto) {

        UserDto user = userService.login(userDto);

        ApiResponse<UserDto> response = new ApiResponse<>();

        response.setMsg("User Login");
        response.setStatus(200);
        response.setPayload(user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping("/reset-pwd")
    public ResponseEntity<ApiResponse<UserDto>> login(@RequestBody ResetPwdDto resetpwdDto) {

        UserDto user = userService.resetPwd(resetpwdDto);

        ApiResponse<UserDto> response = new ApiResponse<>();

        response.setMsg("User pwd Update");
        response.setStatus(200);
        response.setPayload(user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/quote")
    public ResponseEntity<ApiResponse<QuoteApiResponseDto>> getQuote() {

        QuoteApiResponseDto quote = userService.getQuote();

        ApiResponse<QuoteApiResponseDto> response = new ApiResponse<>();

        response.setMsg("Random Quote");
        response.setStatus(200);
        response.setPayload(quote);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
