package com.sydoruk.servlets;

import com.sydoruk.model.Detail;
import com.sydoruk.service.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

@WebServlet(name = "Statistics", value = "/stats")
public class StatisticsServlet extends HttpServlet {

    final static Logger logger = LoggerFactory.getLogger("StatisticsServlet");

    @Override
    public void init() {
        logger.info(LocalTime.now().toString() + "StatisticsServlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Service service = Service.getInstance();
        final List allId = service.getAllId();
        final String id = request.getParameter("id");
        if (id != null) {
            final Detail detail = service.findById(id);
            request.setAttribute("detail", detail);
            request.setAttribute("all_id", allId);
            request.getRequestDispatcher("statistics.jsp").forward(request, response);
        }
        final String totalDetails = service.getTotalNumberOfDetail();
        final String totalBrokenChips = service.getTotalBrokenChips();
        final String totalExtractedFuel = service.getTotalExtractedFuel();
        final String totalUsedFuel = service.getTotalUsedFuel();
        request.setAttribute("all_id", allId);
        request.setAttribute("total_details", totalDetails);
        request.setAttribute("total_broken_chips", totalBrokenChips);
        request.setAttribute("total_extracted_fuel", totalExtractedFuel);
        request.setAttribute("total_used_fuel", totalUsedFuel);
        request.getRequestDispatcher("statistics.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        logger.info(LocalTime.now().toString() + "StatisticsServlet destroyed");
    }
}