package com.user_management.service;

import com.user_management.dto.*;

import java.util.List;

public interface UserService {
    public UserDto registerUser(UserDto userDto);

    public UserDto login(UserDto userDto);

    public UserDto resetPwd(ResetPwdDto resetPwdDto);

    public QuoteApiResponseDto getQuote();

    public List<CountryDto> getCountries();

    public List<StateDto> getStates(Integer countryId);

    public List<CityDto> getCities(Integer stateId);

    public UserDto getUserByEmail(String email);
}
