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

    public Reward useMoney(String moneyType, int amount, String userName) {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        } else{
            if(moneyType.equals("coin")){
                if(user.getCoin() < amount){
                    throw new IllegalArgumentException("Not enough coin. You have " + user.getCoin());
                } else{
                    user.setCoin(user.getCoin() - amount);
                }
            } else if(moneyType.equals("leaf")){
                if(user.getLeaf() < amount){
                    throw new IllegalArgumentException("Not enough leaf. You have " + user.getLeaf());
                } else{
                    user.setLeaf(user.getLeaf() - amount);
                }
            } else{
                throw new IllegalArgumentException("Invalid money type");
            }
            userRepository.save(user);
            return user.getMoney();
        }
    }
}
