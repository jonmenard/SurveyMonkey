package org.surveymonkey.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.surveymonkey.models.applicationUser;
import org.surveymonkey.repositories.UserRepository;
import org.surveymonkey.services.iservices.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public applicationUser findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public applicationUser findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public applicationUser save(applicationUser applicationUser) {
        return userRepository.save(applicationUser);
    }

}
