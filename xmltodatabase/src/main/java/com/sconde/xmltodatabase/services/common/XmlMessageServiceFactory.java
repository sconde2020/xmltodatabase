package com.sconde.xmltodatabase.services.common;

import com.sconde.xmltodatabase.services.interfaces.XmlMessageService;
import com.sconde.xmltodatabase.services.people.PeopleMessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static com.sconde.xmltodatabase.utils.MessageTag.PEOPLE_MESSAGE_TAG;

@Service
@Log4j2
public class XmlMessageServiceFactory {

    @Autowired
    private PeopleMessageService peopleMessageService;

    private Map<String, XmlMessageService> serviceMap;

    @PostConstruct
    public void init() {
        serviceMap = new HashMap<>();
        serviceMap.put(PEOPLE_MESSAGE_TAG, peopleMessageService);
    }

    public XmlMessageService getService(String tag) {
        return serviceMap.get(tag);
    }
}
