package com.user_management.service;
import com.user_management.dto.*;
import com.user_management.entity.City;
import com.user_management.entity.State;
import com.user_management.entity.UserMaster;
import org.modelmapper.ModelMapper;
import com.user_management.entity.Country;
import com.user_management.repo.CityRepo;
import com.user_management.repo.CountryRepo;
import com.user_management.repo.StateRepo;
import com.user_management.repo.UserMasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private CountryRepo countryRepo;
    @Autowired
    private StateRepo stateRepo;
    @Autowired
    private CityRepo cityRepo;

    @Autowired
     UserMasterRepo userMasterRepo;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<CountryDto>  getCountries(){
       List<Country> countries = countryRepo.findAll();
       return countries.stream().map(country->modelMapper.map(country,CountryDto.class)).toList();
    }
    @Override
    public List<StateDto> getStates(Integer countryId) {
        List<State> states = stateRepo.findByCountryCountryId(countryId);
        return states
                .stream()
                .map(state -> modelMapper.map(state, StateDto.class))
                .toList();
    }
    @Override
    public List<CityDto> getCities(Integer stateId) {
        List<City> cities = cityRepo.findByStateStateId(stateId);
        return cities
                .stream()
                .map(city -> modelMapper.map(city, CityDto.class))
                .toList();
    }
    @Override
    public UserDto registerUser(UserDto userDto){
        if (userMasterRepo.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        UserMaster entity = modelMapper.map(userDto, UserMaster.class);
        Country country=countryRepo.findById(userDto.getCountryId()).orElseThrow();
        State state=stateRepo.findById(userDto.getStateId()).orElseThrow();
        City city = cityRepo.findById(userDto.getCityId()).orElseThrow();
        entity.setCountry(country);
        entity.setState(state);
        entity.setCity(city);
        entity.setPwdUpdated("NO");
        String tempPwd=generateTemporaryPassword();
        entity.setPwd(tempPwd);
        UserMaster savedEntity=userMasterRepo.save(entity);
        String subject = "Your Account created | AshokIT";
        String body = "Your Loing Password : " + tempPwd;
        emailService.sendEmail(userDto.getEmail(), subject, body);
        return modelMapper.map(savedEntity, UserDto.class);

    }
    @Override
    public UserDto login(UserDto userDto) {
        Optional<UserMaster> opt = userMasterRepo.findByEmailAndPwd(userDto.getEmail(), userDto.getPwd());
        if(opt.isPresent()){
            UserMaster userMaster = opt.get();
            return modelMapper.map(userMaster, UserDto.class);
        }
        return null;
    }
    @Override
    public UserDto resetPwd(ResetPwdDto resetPwdDto) {
        Optional<UserMaster> byEmail = userMasterRepo.findByEmail(resetPwdDto.getEmail());
        if(byEmail.isPresent()) {
            UserMaster userMaster = byEmail.get();
            userMaster.setPwd(resetPwdDto.getNewPwd());
            userMaster.setPwdUpdated("YES");
            UserMaster saved = userMasterRepo.save(userMaster);
            return modelMapper.map(saved, UserDto.class);
        }
        return null;
    }
    @Override
    public QuoteApiResponseDto getQuote() {

        String apiUrl = "https://dummyjson.com/quotes/random";

        RestTemplate rt = new RestTemplate();

        ResponseEntity<QuoteApiResponseDto> forEntity = rt.getForEntity(apiUrl, QuoteApiResponseDto.class);

        return forEntity.getBody();

    }
    @Override
    public UserDto getUserByEmail(String email) {
        Optional<UserMaster> byEmail = userMasterRepo.findByEmail(email);
        if(byEmail.isPresent()) {
            UserMaster userMaster = byEmail.get();
            return modelMapper.map(userMaster, UserDto.class);
        }
        return null;
    }

    private String generateTemporaryPassword(){
        String chars="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789#@$";
        Random random=new Random();
        StringBuffer buffer=new StringBuffer();
        for(int i=0;i<6;i++){
            int index=random.nextInt(chars.length());
            char ch=chars.charAt(index);
            buffer.append(ch);
        }
        return buffer.toString();
    }
}
