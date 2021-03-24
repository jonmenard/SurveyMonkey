package org.surveymonkey.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.surveymonkey.models.EndUser;
import org.surveymonkey.repositories.EndUserRepository;
import org.surveymonkey.services.iservices.IEndUserService;

@Service
public class EndUserService implements IEndUserService {

    @Autowired
    EndUserRepository endUserRepository;

    @Override
    public EndUser findById(long id) {
        return endUserRepository.findById(id);
    }

    @Override
    public EndUser findByName(String name) {
        return endUserRepository.findByName(name);
    }

    @Override
    public EndUser save(EndUser endUser) {
        return endUserRepository.save(endUser);
    }

}