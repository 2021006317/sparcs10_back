package com.sparcs10.demo.service;

import com.sparcs10.demo.entity.User;
import com.sparcs10.demo.repository.UserRepository;
import com.sparcs10.demo.utils.Reward;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public Reward getMoney(String userName) {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        } else{
            return user.getMoney();
        }
    }
}
