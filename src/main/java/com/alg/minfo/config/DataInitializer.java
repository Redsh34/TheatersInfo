package com.alg.minfo.config;
import com.alg.minfo.entity.Role;
import com.alg.minfo.entity.user;
import com.alg.minfo.repo.customrepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final customrepo userRepository;

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner init(customrepo userRepository) {
        return args -> {

            if (userRepository.findByUsername("virat").isEmpty()) {

                user u = new user();
                u.setUsername("virat");
                u.setPassword(passwordEncoder.encode("1234"));
                u.setRole(Role.ADMIN);

                userRepository.save(u);

                System.out.println("Dummy user created");
            }
        };
    }
}
