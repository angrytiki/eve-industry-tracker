package controllers;

import play.*;
import play.api.templates.Html;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
  
    public static Result index() {
    	Html out = views.html.index.render("Hello, world!");
        return ok(out);
    }
  
}
