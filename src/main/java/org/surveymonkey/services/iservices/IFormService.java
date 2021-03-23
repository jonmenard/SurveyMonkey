package org.surveymonkey.services.iservices;

import org.surveymonkey.models.Form;

public interface IFormService {

    Form findById(long id);

}