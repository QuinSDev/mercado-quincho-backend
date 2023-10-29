package com.mercado.quincho.service;

import com.mercado.quincho.entity.Photo;
import com.mercado.quincho.entity.Role;
import com.mercado.quincho.entity.User;
import com.mercado.quincho.repository.UserRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author QuinSDev
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public void registerUser(String name, String lastName,
            String address, String phoneNumber, String cellPhoneNumber, 
            String email, String password) {
        
        User user = new User();
        
        user.setName(name);
        user.setLastName(lastName);
        user.setAddress(address);
        user.setPhoneNumber(phoneNumber);
        user.setCellPhoneNumber(cellPhoneNumber);
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setRol(Role.CUSTOMER);
        
        userRepository.save(user);
        
    }
}
