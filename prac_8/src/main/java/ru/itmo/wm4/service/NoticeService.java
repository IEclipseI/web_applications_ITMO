package ru.itmo.wm4.service;

import org.springframework.stereotype.Service;
import ru.itmo.wm4.domain.Notice;
import ru.itmo.wm4.domain.Tag;
import ru.itmo.wm4.domain.User;
import ru.itmo.wm4.form.NoticeCredentials;
import ru.itmo.wm4.repository.NoticeRepository;
import ru.itmo.wm4.repository.TagRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private final TagRepository tagRepository;

    public NoticeService(NoticeRepository noticeRepository, TagRepository tagRepository) {
        this.noticeRepository = noticeRepository;
        this.tagRepository = tagRepository;
    }

    public List<Notice> findByOrderByCreationTimeDesc() {
        return noticeRepository.findByOrderByCreationTimeDesc();
    }

    public void save(NoticeCredentials noticeCredentials, User user) {
        Set<String> set = new HashSet<>(Arrays.asList(noticeCredentials.getStringWithTags().split("\\s+")));
        Notice notice = new Notice();
        notice.setText(noticeCredentials.getText());
        for (String tagName : set) {
            Tag tag = new Tag();
            tag.setName(tagName);
            if (tagRepository.findByName(tagName) == null) {
                tagRepository.save(tag);
            }
            notice.addTag(tagRepository.findByName(tagName));
        }
        user.addNotice(notice);
        notice.setUser(user);
        noticeRepository.save(notice);
    }

    public Notice findById(Long noticeId) {
        return noticeId == null ? null : noticeRepository.findById(noticeId).orElse(null);
    }
}
