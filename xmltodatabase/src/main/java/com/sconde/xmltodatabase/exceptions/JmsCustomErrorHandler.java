package com.sconde.xmltodatabase.exceptions;

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.util.ErrorHandler;

@Service
@Log4j2
public class JmsCustomErrorHandler implements ErrorHandler {
    @SneakyThrows
    @Override
    public void handleError(Throwable t) {
        log.error(t.getCause().getMessage());
        Thread.sleep(10000);
    }
}
