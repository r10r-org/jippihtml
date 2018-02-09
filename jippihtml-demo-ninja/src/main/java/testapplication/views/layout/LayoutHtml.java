package testapplication.views.layout;

import static org.r10r.jippihtml.JippiHtml.*;

/**
 * Demo of a layout page that will be "inherited" by the template called by the
 * user.
 */
public class LayoutHtml {

  public static HtmlElement template(String titleText, HtmlElement htmlElement) {
    return
      document(
        doctypeHtml(),
        html(
          head(
            title(titleText)
          ),
          body(
            HeaderHtml.template(),
            htmlElement,
            FooterHtml.template()
          )
        )
      );
  }

}
