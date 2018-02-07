package testapplication.views;

import testapplication.views.layout.LayoutHtml;
import static org.r10r.jippihtml.JippiHtml.*;

/**
 * That's a main template a user would use.
 */
public class IndexPage {
    
    public static HtmlElement html(String title) {
      
      return
        LayoutHtml.render("title", 
          div(
            p("that's the main body!"))
        );
        
        
//        $(x(xssString));
//        $("<div class='main'><p>a message</p></div>");
//        
//        $("<p>... and tha's a i18n message, dude: ");
//        $(juckulaI18n.getWithDefault("i18n.test", "defaultMessage"));
//        $("</p>");
//        
//        $("<ul>");
//        for (int i = 0; i < 10; i++) {
//            $("<li>");
//            $(linkTagProvider.get().html("title" + i, "href" + i));
//            $("</li>");
//        }
//        $("</ul>");
//
//        LayoutHtml layoutHtml = layoutHtmlProvider.get();
//        layoutHtml.html(title, this);
//        $parent(layoutHtml);
//        
//        return this;
        
    }

}
