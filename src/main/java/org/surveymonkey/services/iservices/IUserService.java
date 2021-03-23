package org.surveymonkey.services.iservices;

import org.surveymonkey.models.applicationUser;

public interface IUserService {

    applicationUser findById(long id);

    applicationUser findByName(String name);

    applicationUser save(applicationUser applicationUser);

}