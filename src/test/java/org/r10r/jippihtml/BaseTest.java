package org.r10r.jippihtml;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.r10r.jippihtml.Base.*;
import org.hamcrest.CoreMatchers;

public class BaseTest {

  public BaseTest() {
  }

  // That's a site template, where you can include the variable parts
  private HtmlElement siteTemplate(HtmlElement... mainPage) {
    return html(
      head(
        title("My awesome website")
      ),
      body(
        mainPage
      )
    );

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