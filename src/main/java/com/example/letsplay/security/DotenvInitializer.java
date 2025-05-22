package com.example.letsplay.security;
import io.github.cdimascio.dotenv.Dotenv;

public class DotenvInitializer {
    static {
        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        dotenv.entries().forEach(entry -> {
            // Inject into system properties so Spring can access it
            System.setProperty(entry.getKey(), entry.getValue());
        });
    }
}
