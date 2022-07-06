package com.company.controller;

import com.company.dto.profile.ChangeDetailsDTO;
import com.company.dto.profile.ChngPswdDTO;
import com.company.dto.ResponseInfoDTO;
import com.company.dto.profile.ProfileDTO;
import com.company.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
@Autowired
private ProfileService profileService;
    @PutMapping("/changePassword")
    public ResponseEntity<ResponseInfoDTO> changePassword( @RequestBody ChngPswdDTO chngPswdDTO){

        ResponseInfoDTO response = profileService.changePassword(chngPswdDTO.getPassword());
        return ResponseEntity.ok().body(response);

    }
    @PutMapping("/changeDetails")
    public ResponseEntity<ResponseInfoDTO> changeDetails( @RequestBody ChangeDetailsDTO dto){

        ResponseInfoDTO response = profileService.changeDetails(dto.getUsername());
        return ResponseEntity.ok().body(response);

    }
    @PutMapping("/changeEmail")
    public ResponseEntity<String> changeEmail( @RequestBody ProfileDTO dto){

        String response = profileService.changeEmail(dto.getTempEmail());
        return ResponseEntity.ok().body(response);

    }
}
