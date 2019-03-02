package ru.itmo.wp.servlet;

import com.google.gson.Gson;
//import com.sun.org.apache.xpath.internal.operations.String;
//import com.sun.org.apache.xpath.internal.operations.String;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;


public class StaticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        String[] ar = uri.split("[+]");
        response.setContentType(getContentTypeFromName(ar[0]));
        OutputStream outputStream = response.getOutputStream();
        for (String s:ar) {
            File file = new File("./src/main/webapp/static/" + s);
            if (file.isFile()) {
                Files.copy(file.toPath(), outputStream);
                outputStream.flush();
            } else {
                file = new File(getServletContext().getRealPath("/static/" + s));
                if (file.isFile()) {
                    Files.copy(file.toPath(), outputStream);
                    outputStream.flush();
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            }
        }
    }

    private String getContentTypeFromName(String name) {
        name = name.toLowerCase();

        if (name.endsWith(".png")) {
            return "image/png";
        }

        if (name.endsWith(".jpg")) {
            return "image/jpeg";
        }

        if (name.endsWith(".html")) {
            return "text/html";
        }

        if (name.endsWith(".css")) {
            return "text/css";
        }

        if (name.endsWith(".js")) {
            return "application/javascript";
        }

        throw new IllegalArgumentException("Can't find content type for '" + name + "'.");
    }
}
