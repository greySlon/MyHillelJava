package com.slon.servlets;

/**
 * Created by Sergii on 23.04.2017.
 */

import java.io.*;
import java.text.MessageFormat;
import java.util.Map;
import javax.servlet.*;
import javax.servlet.http.*;

public class Serv extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        PrintWriter out = response.getWriter();

        String query = request.getQueryString();
        if (query != null && !query.isEmpty()) {
            for (String pair : query.split("&")) {
                String[] a = pair.split("=");
                out.println(MessageFormat.format("{0}={1}", a[0], a[1]));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Map<String, String[]> mapParam = req.getParameterMap();
        for (Map.Entry<String, String[]> entry : mapParam.entrySet()) {
            out.println(MessageFormat.format("{0}={1}", entry.getKey(), entry.getValue()[0]));
        }
    }
}