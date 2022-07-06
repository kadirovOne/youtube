package com.company.controller;

import com.company.dto.profile.ProfileDTO;
import com.company.dto.RegistrationDTO;
import com.company.dto.ResponseInfoDTO;
import com.company.service.AuthService;
import com.company.service.ProfileService;
import com.company.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private ProfileService profileService;
    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody RegistrationDTO dto) {
        String ver = authService.registration(dto);
        return ResponseEntity.ok(ver);
    }
    @GetMapping("/email/verification/{jwt}")
    public ResponseEntity<ProfileDTO> login( @PathVariable("jwt") String jwt) {
        Integer profileId = JwtUtil.decode(jwt);
        ProfileDTO profileDTO = authService.login(profileId);
        return ResponseEntity.ok(profileDTO);
    }
    @PostMapping("email/updateEmail/{email}")
    public ResponseEntity<ResponseInfoDTO> update(@PathVariable("email") String email) {
        ResponseInfoDTO response = authService.changeEmail(email);
        return ResponseEntity.ok(response);
    }


}
