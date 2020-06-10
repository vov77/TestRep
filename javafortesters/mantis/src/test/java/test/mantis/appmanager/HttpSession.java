package test.mantis.appmanager;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpSession {
  private CloseableHttpClient httpClient;
  private AppManager app;

  public HttpSession (AppManager app){
    this.app = app;
    // new http session is created with redirect setting to forward 302 response automatically
    httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
  }

  public  boolean login (String username, String password) throws IOException{
    //new empty Post request is created to "web.baseUrl" + "/login.php"
    HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php");
    //created params for Post request
    List<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("username", username));
    //params.add(new BasicNameValuePair("secure_session", "on"));
    params.add(new BasicNameValuePair("return", "index.php"));
    params.add(new BasicNameValuePair("password", password));
    //params are enclosed inside Post request
    post.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
    //Post request is sent, response == is response from server which can be parsed
    CloseableHttpResponse response = httpClient.execute(post);
    // verification that the page contains text with username (administrator)
    String body = getTextFrom(response);
    //System.out.println(body);
    //for admin
    //return body.contains(String.format("span class=\"label hidden-xs label-default arrowed\">%s</span>", username));
    //for new user
    return body.contains(String.format("span class=\"user-info\">%s</span>", username));
  }

  public boolean isLoggedInAs (String username) throws IOException {
    //new Get request created
    HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/index.php");
    CloseableHttpResponse response = httpClient.execute(get);
    String body = getTextFrom(response);
    //System.out.println(body);
    return body.contains((String.format("span class=\"label hidden-xs label-default arrowed\">%s</span>", username)));
  }

  private String getTextFrom(CloseableHttpResponse response) throws IOException {
    try {
        return EntityUtils.toString(response.getEntity());
      } finally {
        response.close();
      }
    }
  }

