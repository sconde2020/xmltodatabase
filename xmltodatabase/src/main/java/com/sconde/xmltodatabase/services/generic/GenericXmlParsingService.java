package com.sconde.xmltodatabase.services.generic;

import com.sconde.xmltodatabase.services.interfaces.XmlParsingService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.sconde.xmltodatabase.utils.DebugMessage.end;
import static com.sconde.xmltodatabase.utils.DebugMessage.start;

@Service
@Log4j2
public class GenericXmlParsingService implements XmlParsingService {

    @Override
    public Object parse(String xmlMessage, String sourcesPath)  {
        start("parse");

        Object parsedObject = null;
        try {
            JAXBContext jc = JAXBContext.newInstance(sourcesPath);
            Unmarshaller unmarshaller = jc.createUnmarshaller();

            InputStream inputStream = new ByteArrayInputStream(xmlMessage.getBytes(StandardCharsets.UTF_8));
            parsedObject = unmarshaller.unmarshal(inputStream);
            log.debug("Message parsed successfully");
        } catch (Exception e) {
            log.error("Parsing Exception: " + e);
        }

        end("parse");
        return parsedObject;
    }
}
