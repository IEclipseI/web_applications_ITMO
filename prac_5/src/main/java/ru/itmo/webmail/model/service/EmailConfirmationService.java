package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.domain.EmailConfirmation;
import ru.itmo.webmail.model.repository.EmailConfirmationRepository;
import ru.itmo.webmail.model.repository.impl.EmailConfirmationRepositoryImpl;

import java.nio.charset.Charset;
import java.util.Random;

public class EmailConfirmationService {
    private EmailConfirmationRepository emailConfirmationRepository = new EmailConfirmationRepositoryImpl();

    public void save(EmailConfirmation emailConfirmation) {
        emailConfirmationRepository.save(emailConfirmation);
    }

    public EmailConfirmation findByUserId(long userId) {
        return emailConfirmationRepository.findByUserId(userId);
    }

    public EmailConfirmation findBySecret(String secret) {
        return emailConfirmationRepository.findBySecret(secret);
    }

    public String generateSecret() {
        int leftLimit = 'a'; // letter 'a'
        int rightLimit = 'z'; // letter 'z'
        Random random = new Random();
        int targetStringLength = 50;
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (Math.abs(random.nextInt()) % ('z' - 'a'));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
