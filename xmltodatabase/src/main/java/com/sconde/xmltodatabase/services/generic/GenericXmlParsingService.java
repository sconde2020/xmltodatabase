package com.sconde.xmltodatabase.services.generic;

import com.sconde.xmltodatabase.exceptions.ParsingException;
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
    public Object parse(String xmlMessage, String sourcesPath) throws ParsingException {
        try {
            start("parse");

            Object parsedObject;
            InputStream inputStream = new ByteArrayInputStream(xmlMessage.getBytes(StandardCharsets.UTF_8));

            JAXBContext jc = JAXBContext.newInstance(sourcesPath);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            parsedObject = unmarshaller.unmarshal(inputStream);
            log.debug("Message parsed successfully");

            end("parse");
            return parsedObject;
        } catch (Exception e) {
            log.error(e.toString());
            throw new ParsingException("Error when parsing xml message");
        }
    }
}
