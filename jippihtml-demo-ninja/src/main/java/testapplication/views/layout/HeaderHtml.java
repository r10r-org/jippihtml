package testapplication.views.layout;

import static org.r10r.jippihtml.JippiHtml.*;
import org.r10r.jippihtml.JippiHtml.HtmlElement;

/**
 * A header of a webpage. Something that many webpages of your app would use...
 */
public class HeaderHtml {

  public static HtmlElement html() {

    return 
      header(
        p("That's the funky header, dude!")
      );
  }

}
