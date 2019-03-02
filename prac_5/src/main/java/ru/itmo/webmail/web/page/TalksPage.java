package ru.itmo.webmail.web.page;

import ru.itmo.webmail.model.domain.Talk;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class TalksPage extends Page {
    private void action(HttpServletRequest request, Map<String, Object> view) {

    }

    private void sendMessage(HttpServletRequest request, Map<String, Object> view) {
        User user = getUserService().findByLogin((String) request.getParameter("to"));
        if (user != null) {
            if (getUser().getId() != user.getId()) {
                Talk talk = new Talk();
                talk.setSourceUserId(((Long) request.getSession().getAttribute(USER_ID_SESSION_KEY)));
                talk.setTargetUserId(user.getId());
                talk.setText((String) request.getParameter("text"));
                try {
                    getTalkService().validateTalk(talk);
                    getTalkService().saveTalk(talk);
                } catch (ValidationException e) {
                    view.put("error", e.getMessage());
                    view.put("to", (String) request.getParameter("to"));
                }
            } else {
                view.put("error", "You can't send messages to yourself");
                view.put("message", (String) request.getParameter("text"));
            }
        } else {
            view.put("error", "User does not exist");
            view.put("message", (String) request.getParameter("text"));
        }
    }

    @Override
    public void after(HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
        if (getUser() == null) {
            throw new RedirectException("/index");
        }
        List<Talk> talks = getTalkService().findAllWithUserId(getUser().getId());
        List<Talk.TalkView> views = new ArrayList<>();
        for (Talk talk : talks) {
            views.add(new Talk.TalkView(getUserService().find(talk.getSourceUserId()).getLogin(),
                    getUserService().find(talk.getTargetUserId()).getLogin(),
                    talk.getText(),
                    talk.getCreationTime().toString()/*"hz"*/));
        }
        view.put("talksView", views);
    }
}
