package testapplication.views.layout;

import static org.r10r.jippihtml.JippiHtml.*;

/**
 * That's the footer.
 */
public class FooterHtml {

  public static HtmlElement template() {
    return
      footer(
        p("That's the funky footer, dude!")
      );
  }

}
