package ru.itmo.webmail.model.repository;

import ru.itmo.webmail.model.domain.Talk;
import ru.itmo.webmail.model.domain.User;

import java.util.List;

public interface TalkRepository {
    List<Talk> findAllWithUserId(Long userId);
    void save(Talk talk);
}
