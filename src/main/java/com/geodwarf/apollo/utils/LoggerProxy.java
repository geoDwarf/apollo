package com.geodwarf.apollo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerProxy {

    public Logger getLogger(Object classToLog){
         return LoggerFactory.getLogger(classToLog.getClass());
    }
}
