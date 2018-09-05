package com.isa.web;

import com.isa.data.MessageRepository;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/messages")
public class MessageServlet extends HttpServlet {

    @Inject
    private MessageRepository messageRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final PrintWriter printWriter = resp.getWriter();
        messageRepository.getMessages().forEach((date, msg) -> {
            printWriter.println(date + ": " + msg);
        });
    }
}
