package testapplication.conf;

import com.google.inject.Singleton;
import java.io.IOException;
import java.io.Writer;
import ninja.Context;
import ninja.Result;
import ninja.template.TemplateEngine;
import ninja.utils.ResponseStreams;
import org.r10r.jippihtml.JippiHtml;
import org.r10r.jippihtml.JippiHtml.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ra
 */
@Singleton
public class JippiHtmlNinja implements TemplateEngine {

  Logger logger = LoggerFactory.getLogger(JippiHtmlNinja.class);

  @Override
  public void invoke(Context context, Result result) {

    if (result.getRenderable() instanceof JippiHtml.HtmlElement) {

      ResponseStreams responseStreams = context.finalizeHeaders(result);
      HtmlElement htmlElement = (HtmlElement) result.getRenderable();

      try (Writer writer = responseStreams.getWriter()) {
        htmlElement.renderToWriter(writer);
      } catch (IOException ioException) {
        logger.error("somthing bad happended while writing out template", ioException);
      }
    }

  }

  @Override
  public String getSuffixOfTemplatingEngine() {
    return null;
  }

  @Override
  public String getContentType() {
    return "text/html";
  }

}
