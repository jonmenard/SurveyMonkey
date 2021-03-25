package org.surveymonkey.services.iservices;

import org.surveymonkey.models.EndUser;

public interface IEndUserService {

    EndUser findById(long id);

    EndUser findByName(String name);

    EndUser save(EndUser endUser);

}