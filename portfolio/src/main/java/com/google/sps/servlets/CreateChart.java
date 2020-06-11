package com.google.sps.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/vsVote-data")
public class CreateChart extends HttpServlet{
    private Map<String, Integer> videoVotes = new HashMap<>();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("application/json");
        String json = new Gson().toJson(videoVotes);
        response.getWriter().println(json);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String video = request.getParameter("video");
        int currentVotes = videoVotes.containsKey(video) ? videoVotes.get(video) : 0;
        videoVotes.put(video, currentVotes + 1);

        response.sendRedirect("/Chart.html");
    }
} 
