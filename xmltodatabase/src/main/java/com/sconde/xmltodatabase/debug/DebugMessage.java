package com.sconde.xmltodatabase.debug;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class DebugMessage {

    public static void start(String function) {
        log.debug(" ");
        log.debug(function + " starts");
    }

    public static void end(String function) {
        log.debug(function + " ends");
    }
}
