package com.alg.minfo.service;

/**
 * Interface for logging service (different implementations per profile)
 */
public interface LoggingService {
    void logMovieSaved(String movieName);
    void logTheatreSaved(String theatreName);
    void logInfo(String message);
}

