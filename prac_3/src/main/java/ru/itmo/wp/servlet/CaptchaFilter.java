package ru.itmo.wp.servlet;


import ru.itmo.wp.util.ImageUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class CaptchaFilter extends HttpFilter {
    private static final String CAPTCHA_VALUE = "captcha-value";
    private static final String CAPTCHA_HASH = "captcha-hash";

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getRequestURI().endsWith("/favicon.ico")) {
            chain.doFilter(request, response);
        } else {
            if (request.getSession().getAttribute(CAPTCHA_HASH) == null) {
                redirectToCaptcha(request, response);
            } else {
                if (request.getSession().getAttribute(CAPTCHA_VALUE) == null) {
                    if (request.getParameter(CAPTCHA_VALUE) != null &&
                            getCaptchaHash(request.getParameter(CAPTCHA_VALUE)).equals(request.getSession().getAttribute(CAPTCHA_HASH))) {
                        request.getSession().setAttribute(CAPTCHA_VALUE, request.getParameter(CAPTCHA_VALUE));
                        response.sendRedirect(request.getRequestURI().split("\\?")[0]);
                        chain.doFilter(request, response);
                    } else {
                        redirectToCaptcha(request, response);
                    }
                } else {
                    chain.doFilter(request, response);
                }
            }
        }
    }

    private String getCaptchaHash(String s) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
        byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte aHash : hash) {
            String hex = Integer.toHexString(0xff & aHash);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    private String buildCaptchaPage(String captchaImg) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>captcha</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<main>\n" +
                "    <div class=\"box\">\n" +
                "        <img src=\"data:image/png;base64," +
                captchaImg
                + "\" alt=\"test\">\n" +
                "        <form method=\"get\">\n" +
                "            <input type=\"text\" name=\"captcha-value\">\n" +
                "        </form>\n" +
                "    </div>\n" +
                "</main>\n" +
                "</body>\n" +
                "</html>";
    }

    private void redirectToCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int captcha_value = 100 + new Random(System.currentTimeMillis()).nextInt(900);
        request.getSession().setAttribute(CAPTCHA_HASH, getCaptchaHash(Integer.toString(captcha_value)));
        response.setContentType("text/html");
        response.getOutputStream().print(buildCaptchaPage(Base64.getEncoder().encodeToString(ImageUtils.toPng(Integer.toString(captcha_value)))));
    }
}
