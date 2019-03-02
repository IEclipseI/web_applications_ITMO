package ru.itmo.wm4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wm4.domain.Comment;
import ru.itmo.wm4.domain.Notice;
import ru.itmo.wm4.domain.Role;
import ru.itmo.wm4.form.NoticeCredentials;
import ru.itmo.wm4.security.AnyRole;
import ru.itmo.wm4.security.Guest;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class NoticePage extends Page {
    @AnyRole(Role.Name.ADMIN)
    @GetMapping(path = "/notice")
    public String noticeGet(Model model) {
        model.addAttribute("notice", new NoticeCredentials());
        return "NoticePage";
    }

    @AnyRole(Role.Name.ADMIN)
    @PostMapping(path = "/notice")
    public String noticePost(@Valid @ModelAttribute("notice") NoticeCredentials noticeCredentials,
                               BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "NoticePage";
        }
        getNoticeService().save(noticeCredentials, getUser(httpSession));
        return "redirect:/notices";
    }

    @AnyRole({Role.Name.ADMIN, Role.Name.USER})
    @GetMapping(path = "/notice/{id}")
    public String noticeGet(@PathVariable Long id, Model model) {
        model.addAttribute("noticeInfo", getNoticeService().findById(id));
        return "NoticeInfoPage";
    }

    @AnyRole({Role.Name.USER, Role.Name.ADMIN})
    @PostMapping(path = "/notice/{id}")
    public String noticeCommentPost(@PathVariable Long id, @Valid @ModelAttribute("comment") Comment comment,
                                    BindingResult bindingResult,
                                     HttpSession httpSession, Model model) {
        comment.setId(null);
        if (bindingResult.hasErrors()) {
            model.addAttribute("noticeInfo", getNoticeService().findById(id));
            return "NoticeInfoPage";
        }

        getCommentService().save(comment, getUser(httpSession), getNoticeService().findById(id));
        return "redirect:/notice/" + id;
    }
}
