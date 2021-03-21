package org.surveymonkey.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.surveymonkey.models.User;
import org.surveymonkey.repositories.UserRepository;
import org.surveymonkey.services.iservices.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

}
