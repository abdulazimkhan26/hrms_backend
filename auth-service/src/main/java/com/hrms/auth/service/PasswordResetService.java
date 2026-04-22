package com.hrms.auth.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hrms.auth.dto.ForgotPasswordResponse;
import com.hrms.auth.dto.ResetPasswordResponse;
import com.hrms.auth.entity.User;
import com.hrms.auth.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    public ForgotPasswordResponse forgotPassword(String email) {
        Optional<User> Optionaluser = userRepository.findByEmail(email);

        if(!Optionaluser.isPresent()){
            return new ForgotPasswordResponse("No account for register with provided email!", false);
        }

        User user = Optionaluser.get();

        // Generate token
        // 6 char alphanumeric eg: 6A234G
        String token = UUID.randomUUID().toString().replace("-", "").substring(0, 6).toUpperCase();         
        user.setResetToken(token);
        user.setResetExpires(LocalDateTime.now().plusMinutes(3)); // expires in 3 mins
        userRepository.save(user);

        // Send email
        try{        
        sendResetEmail(email, token);
        return new ForgotPasswordResponse("Verification Code sent to mail!", true);
        }
        catch(Exception e){
          return new ForgotPasswordResponse(e.getMessage(), false);
        }
    }

    public ResetPasswordResponse resetPassword(String token, String newPassword) {
        Optional<User> Optionaluser = userRepository.findByResetToken(token);

        User user = Optionaluser.get();
        LocalDateTime expiry = user.getResetExpires();

        if(Optionaluser.isPresent() && LocalDateTime.now().isAfter(expiry)){    
            user.setResetExpires(null);   
            user.setResetToken(null); 
            return new ResetPasswordResponse("Verification code have expired! \nPLease try again.", false);
        }
        if(!Optionaluser.isPresent()){        
            return new ResetPasswordResponse("Verification code invalid! \nPLease try again.", false);
        }

        // Update password
        user.setPassword_hash(passwordEncoder.encode(newPassword));
        user.setUpdatedAt(LocalDateTime.now());
        user.setResetToken(null);       // clear token
        user.setResetExpires(null); // clear expiry
        userRepository.save(user);

        return new ResetPasswordResponse("Password changed successfully. \nRedirecting to login!", true);
    }

    private void sendResetEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText(token);
        mailSender.send(message);
    }
}
