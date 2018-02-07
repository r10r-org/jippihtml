package testapplication.views.layout;


import java.util.Arrays;
import static org.r10r.jippihtml.JippiHtml.*;

/**
 * Demo of a layout page that will be "inherited" by the template called by the
 * user.
 */
public class LayoutHtml {

  public static HtmlElement render(String titleText, HtmlElement htmlElement) {
    
   return
    html(
      head(
        title(titleText)
      ),
      body(
        HeaderHtml.html(),
        htmlElement,
        FooterHtml.html()
      )
    );
  }

}
