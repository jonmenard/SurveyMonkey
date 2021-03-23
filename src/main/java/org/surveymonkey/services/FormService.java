package org.surveymonkey.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.surveymonkey.models.Form;
import org.surveymonkey.repositories.FormRepository;
import org.surveymonkey.services.iservices.IFormService;

@Service
public class FormService implements IFormService {

    @Autowired
    FormRepository formRepository;

    @Override
    public Form findById(long id) {
        return formRepository.findById(id);
    }

}
