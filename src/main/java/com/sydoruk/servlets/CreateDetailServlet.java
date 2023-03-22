package com.sydoruk.servlets;

import com.sydoruk.model.Detail;
import com.sydoruk.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;


@WebServlet(name = "CreateDetail", value = "/start")
public class CreateDetailServlet extends HttpServlet {

    final static Logger logger = LoggerFactory.getLogger("StatisticsServlet");

    @Override
    public void init() {
        logger.info(LocalTime.now().toString() + "CreateDetailServlet initialized");
    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Detail detail = Service.getInstance().createdAndSave();
        request.setAttribute("detail", detail);
        RequestDispatcher dispatcher = request.getRequestDispatcher("create.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void destroy() {
        logger.info(LocalTime.now().toString() + "CreateDetailServlet destroyed");
    }
}