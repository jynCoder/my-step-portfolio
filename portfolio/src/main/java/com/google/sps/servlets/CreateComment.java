// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;
import com.google.gson.Gson;
import java.io.IOException;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import com.google.sps.data.Comment;
import com.google.sps.servlets.LoadComments;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/create-comment")
public class CreateComment extends HttpServlet {
  private static final String COMMENT = "comment";
 
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
    
     String text = request.getParameter(COMMENT);
     long timestamp = System.currentTimeMillis();

     Entity commentEntity = new Entity("Comment");
     commentEntity.setProperty("comment", text);
     commentEntity.setProperty("timestamp", timestamp);
     DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
     datastore.put(commentEntity);

     response.sendRedirect("/index.html");
  }

  private String getParameter(HttpServletRequest request, String name, String defaultValue) {
    String value = request.getParameter(name);
    if (value == null) {
      return defaultValue;
    }
    return value;
  }
}