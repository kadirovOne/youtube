package com.company.service;

import com.company.dto.ResponseInfoDTO;
import com.company.dto.profile.ProfileDTO;
import com.company.dto.RegistrationDTO;
import com.company.entity.ProfileEntity;
import com.company.exp.BadRequestException;
import com.company.repository.ProfileRepository;
import com.company.util.JwtUtil;
import com.company.util.MD5Util;
import com.company.util.SpringSecurityUtil;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
@Autowired
private ProfileService profileService;
@Autowired
private ProfileRepository profileRepository;

@Autowired
private EmailService emailService;
    public String registration(RegistrationDTO dto) {
        Optional<ProfileEntity> byEmail = profileRepository.findByEmail(dto.getEmail());
        if (byEmail.isPresent()) {
            throw new BadRequestException("User Already Have");
        }
        ProfileEntity profile=new ProfileEntity();
        profile.setUsername(dto.getUsername());
        profile.setPassword(MD5Util.getMd5(dto.getPassword()));
        profile.setEmail(dto.getEmail());
        ProfileEntity profileEntity = profileRepository.save(profile);
        emailService.sendRegistration(profileEntity.getEmail(),profileEntity.getId());

        return "Message Was Sent";
    }

    public ProfileDTO login(Integer id) {
        ProfileEntity profileEntity = profileService.get(id);
        ProfileDTO dto=new ProfileDTO();
        dto.setCreatedDate(profileEntity.getCreatedDate());
        dto.setUsername(profileEntity.getUsername());
        dto.setEmail(profileEntity.getEmail());
        dto.setPassword(profileEntity.getPassword());
        dto.setJwtToken(JwtUtil.encode(id));

        return dto;
    }

    public ResponseInfoDTO changeEmail(String email) {
        ProfileEntity profile= SpringSecurityUtil.getCurrentUser().getProfile();
        profileRepository.updateEmail(profile.getId(),email);
        return new ResponseInfoDTO(1,"Success");
    }
}
