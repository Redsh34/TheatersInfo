package com.alg.minfo.service.serviceimpl;

import com.alg.minfo.service.LoggingService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Development Logging Service
 * Active only when profile = dev or local
 * Logs in human-readable format with emojis for easier debugging
 */
@Component
@Profile({"dev", "local"})
public class DevLoggingService implements LoggingService {

    @Override
    public void logMovieSaved(String movieName) {
        System.out.println("🎬 [DEV] Movie Saved: " + movieName);
    }

    @Override
    public void logTheatreSaved(String theatreName) {
        System.out.println("🎭 [DEV] Theatre Saved: " + theatreName);
    }

    @Override
    public void logInfo(String message) {
        System.out.println("📝 [DEV] " + message);
    }

}

