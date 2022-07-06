package com.company.service;

import com.company.config.CustomUserDetails;
import com.company.dto.ResponseInfoDTO;
import com.company.entity.ProfileEntity;
import com.company.exp.BadRequestException;
import com.company.repository.ProfileRepository;
import com.company.util.MD5Util;
import com.company.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
@Autowired
private ProfileRepository profileRepository;
@Autowired
private EmailService emailService;
    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).orElseThrow(()->{
            throw new BadRequestException("User Not Found");
        });

    }
    public  ProfileEntity getCurrentUser(){
        CustomUserDetails currentUser = SpringSecurityUtil.getCurrentUser();
        return currentUser.getProfile();
    }

    public ResponseInfoDTO changePassword(String newPassword) {
        ProfileEntity profile=getCurrentUser();
        profileRepository.updatePassword(profile.getId(), MD5Util.getMd5(newPassword));
        return new ResponseInfoDTO(1,"success");
    }

    public ResponseInfoDTO changeDetails(String username) {
        ProfileEntity profile=getCurrentUser();
        profileRepository.updateUsername(profile.getId(), username);
        return new ResponseInfoDTO(1,"success");
    }

    public String changeEmail(String newEmail){
        ProfileEntity profile=getCurrentUser();
        emailService.sendForUpdateEmail(newEmail);
        return "Message Was Sent";
    }
}
