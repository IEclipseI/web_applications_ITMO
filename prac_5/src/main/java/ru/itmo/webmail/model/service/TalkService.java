package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.domain.Talk;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.repository.TalkRepository;
import ru.itmo.webmail.model.repository.impl.TalkRepositoryImpl;

import java.util.List;

public class TalkService {
    private TalkRepository talkRepository = new TalkRepositoryImpl();

    public void validateTalk(Talk talk) throws ValidationException {
        if (talk.getText().length() > 10000) {
            throw new ValidationException("Message is too large!");
        }
        if (talk.getText() == null || talk.getText().isEmpty()) {
            throw new ValidationException("Message is empty");
        }
    }

    public void saveTalk(Talk talk) {
        talkRepository.save(talk);
    }

    public List<Talk> findAllWithUserId(Long userId) {
        return talkRepository.findAllWithUserId(userId);
    }
}
