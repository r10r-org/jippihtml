package org.r10r.jippihtml;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.r10r.jippihtml.JippiHtml.*;
import org.hamcrest.CoreMatchers;

public class JippiHtmlTest {

  public JippiHtmlTest() {
  }

  // That's a site template, where you can include the variable parts
  HtmlElement siteTemplate(HtmlElement... mainPage) {
    return html(
      head(
        title("My awesome website")
      ),
      body(
        mainPage
      )
    );
  }

  HtmlElement indexPage() {
    return siteTemplate(
      h1("This is my index page"),
      div(attributes(className("main")),
        text("this is the main page")
      )
    );
  }
  
  @Test
  public void renderPage() {
    String renderedPage = render(indexPage());
    System.out.println(renderedPage);
  }
  
  
  
  
  @Test
  public void testSomeMethod() {

    HtmlElement fullPage
      = siteTemplate(
        div(
          attributes(className(".rectangle")),
          a(attributes(href("http://myWebservercom"), className(".button-id"), attr("id", "myId")),
            text("firstlink")
          ),
          br(),
          a(
            attributes(href("http://mywebserver2.com"), className(".button-two")), 
            text("secondlinkg")
          )
        ),
        div(
          attributes(className("one")),
          text("a text")
        )
      );

    String renderedHtmlElement = render(fullPage);

    assertThat(renderedHtmlElement,
      CoreMatchers.equalTo("<html><head><title>My awesome website</title></head><body><div class=\".rectangle\"><a href=\"http://myWebservercom\" class=\".button-id\" id=\"myId\">firstlink</a><br/><a href=\"http://mywebserver2.com\" class=\".button-two\">secondlinkg</a></div><div class=\"one\">a text</div></body></html>"));
  }

}