package com.google.sps.servlets;

import com.google.sps.data.Comment;
import com.google.gson.Gson;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query.SortDirection;
import java.util.ArrayList;
import java.util.List;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/load-comments")
public class LoadComments extends HttpServlet{
  private static final String COMMENT = "comment";

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Query query = new Query("Comment").addSort("timestamp", SortDirection.DESCENDING);

    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    PreparedQuery results = datastore.prepare(query);

    List<Comment> messages = new ArrayList<>();
    for (Entity entity : results.asIterable()){
      long id = entity.getKey().getId();
      String text = (String) entity.getProperty("comment");
      messages.add(new Comment(id, text));
    }
    String json = new Gson().toJson(messages);
    
    response.setContentType("application/json;");
    response.getWriter().println(json);

  }
}
