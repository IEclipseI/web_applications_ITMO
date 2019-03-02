package ru.itmo.webmail.model.repository;

import ru.itmo.webmail.model.domain.EmailConfirmation;

public interface EmailConfirmationRepository {
    void save(EmailConfirmation emailConfirmation);
    EmailConfirmation findByUserId(long userId);
    EmailConfirmation findBySecret(String secret);
}
