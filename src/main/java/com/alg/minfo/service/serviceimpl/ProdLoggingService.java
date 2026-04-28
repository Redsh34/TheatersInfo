package com.alg.minfo.service.serviceimpl;

import com.alg.minfo.service.LoggingService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Production Logging Service
 * Active only when profile = prod
 * Logs in JSON format suitable for production log aggregation
 */
@Component
@Profile("prod")
public class ProdLoggingService implements LoggingService {

    @Override
    public void logMovieSaved(String movieName) {
        System.out.println("{\"level\":\"INFO\",\"event\":\"MOVIE_SAVED\",\"movieName\":\"" + movieName + "\",\"environment\":\"PROD\"}");
    }

    @Override
    public void logTheatreSaved(String theatreName) {
        System.out.println("{\"level\":\"INFO\",\"event\":\"THEATRE_SAVED\",\"theatreName\":\"" + theatreName + "\",\"environment\":\"PROD\"}");
    }

    @Override
    public void logInfo(String message) {
        System.out.println("{\"level\":\"INFO\",\"message\":\"" + message + "\",\"environment\":\"PROD\"}");
    }

}

