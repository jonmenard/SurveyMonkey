package org.surveymonkey.services.iservices;

import org.surveymonkey.models.User;

public interface IUserService {

    User findById(long id);

    User findByName(String name);

    User save(User user);

}