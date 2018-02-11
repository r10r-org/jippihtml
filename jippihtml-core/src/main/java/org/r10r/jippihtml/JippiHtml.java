package org.r10r.jippihtml;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

public class JippiHtml {
  
  private static final String TD = "td".intern();
  private static final String TR = "tr".intern();
  private static final String A = "a".intern();

  //////////////////////////////////////////////////////////////////////////////
  // SyntacticSugar
  //////////////////////////////////////////////////////////////////////////////
  public static HtmlElement doctypeHtml() {
    return new Text("<!DOCTYPE html>");
  }

  public static HtmlElement document(HtmlElement docType, HtmlElement html) {
    return new Document(docType, html);
  }

  public static HtmlElement html(HtmlAttribute [] htmlAttributes, HtmlElement... htmlElements) {
    return new HtmlElementImpl("html", htmlAttributes, htmlElements);
  }

  public static HtmlElement html(HtmlElement... htmlElements) {
    return new HtmlElementImpl("html", htmlElements);
  }

  public static HtmlElement title(String text) {
    return new HtmlElementImpl("title", text(text));
  }

  public static HtmlElement body(HtmlElement... htmlElements) {
    return new HtmlElementImpl("body", htmlElements);
  }

  public static HtmlElement head(HtmlElement... htmlElements) {
    return new HtmlElementImpl("head", htmlElements);
  }

  public static HtmlElement div(HtmlElement... htmlElements) {
    return new HtmlElementImpl("div", htmlElements);
  }

  public static HtmlElement div(HtmlAttribute [] htmlAttributes, HtmlElement... htmlElements) {
    return new HtmlElementImpl("div", htmlAttributes, htmlElements);
  }

  public static HtmlElement h1(HtmlAttribute [] htmlAttributes, String text) {
    return new HtmlElementImpl("h1", htmlAttributes, text(text));
  }

  public static HtmlElement h1(String text) {
    return new HtmlElementImpl("h1", text(text));
  }

  public static HtmlElement header(HtmlElement... htmlElements) {
    return new HtmlElementImpl("header", htmlElements);
  }

  public static HtmlElement header(HtmlAttribute [] htmlAttributes, HtmlElement... htmlElements) {
    return new HtmlElementImpl("header", htmlAttributes, htmlElements);
  }

  public static HtmlElement footer(HtmlElement... htmlElements) {
    return new HtmlElementImpl("footer", htmlElements);
  }

  public static HtmlElement footer(HtmlAttribute [] htmlAttributes, HtmlElement... htmlElements) {
    return new HtmlElementImpl("footer", htmlAttributes, htmlElements);
  }

  public static HtmlElement meta(HtmlAttribute... htmlAttributes) {
    return new HtmlElementImpl("meta", htmlAttributes);
  }

  public static HtmlElement link(HtmlAttribute... htmlAttributes) {
    return new HtmlElementImpl("link", htmlAttributes);
  }

  public static HtmlElement script(HtmlAttribute [] htmlAttributes) {
    return new HtmlElementImpl("script", htmlAttributes);
  }

  public static HtmlElement script(HtmlAttribute [] htmlAttributes, String text) {
    return new HtmlElementImpl("script", htmlAttributes, text(text));
  }

  public static HtmlElement style(HtmlAttribute [] htmlAttributes) {
    return new HtmlElementImpl("style", htmlAttributes);
  }

  public static HtmlElement style(HtmlAttribute [] htmlAttributes, String text) {
    return new HtmlElementImpl("style", htmlAttributes, text(text));
  }

  public static HtmlElement p(HtmlAttribute [] htmlAttributes, String text) {
    return new HtmlElementImpl("p", htmlAttributes, text(text));
  }

  public static HtmlElement p(String text) {
    return new HtmlElementImpl("p", text(text));
  }

  public static HtmlElement a(HtmlAttribute [] htmlAttributes, String text) {
    return new HtmlElementImpl(A, htmlAttributes, text(text));
  }

  public static HtmlElement a(HtmlAttribute [] htmlAttributes, HtmlElement... htmlElements) {
    return new HtmlElementImpl(A, htmlAttributes, htmlElements);
  }

  public static HtmlElement a(HtmlElement... htmlElements) {
    return new HtmlElementImpl(A, htmlElements);
  }

  public static HtmlElement ul(HtmlElement... htmlElements) {
    return new HtmlElementImpl("ul", htmlElements);
  }

  //public static HtmlElement ul(List<HtmlElement> htmlElements) {
  //  return new HtmlElementImpl("ul", htmlElements);
  //}

  public static HtmlElement table(HtmlElement... htmlElements) {
    return new HtmlElementImpl("table", htmlElements);
  }

  public static HtmlElement thead(HtmlElement... htmlElements) {
    return new HtmlElementImpl("thead", htmlElements);
  }

  public static HtmlElement tr(HtmlElement... htmlElements) {
    return new HtmlElementImpl(TR, htmlElements);
  }

  public static HtmlElement tr(HtmlAttribute [] htmlAttributes, HtmlElement... htmlElements) {
    return new HtmlElementImpl(TR, htmlElements);
  }

  public static HtmlElement th(String content) {
    return new HtmlElementImpl("th", text(content));
  }

  //public static HtmlElement tbody(HtmlElement [] htmlElements) {
  //  return new HtmlElementImpl("tbody", htmlElements);
  //}

  public static HtmlElement tbody(HtmlElement... htmlElements) {
    return new HtmlElementImpl("tbody", htmlElements);
  }

  public static HtmlElement td(String text) {
    return new HtmlElementImpl(TD, text(text));
  }

  public static HtmlElement td(HtmlElement... htmlElements) {
    return new HtmlElementImpl(TD, htmlElements);
  }

  public static HtmlElement td(HtmlAttribute [] htmlAttributes, HtmlElement... htmlElements) {
    return new HtmlElementImpl(TD, htmlAttributes, htmlElements);
  }

  public static HtmlElement strong(String text) {
    return new HtmlElementImpl("strong", text(text));
  }

  public static HtmlElement li(HtmlElement... htmlRenderable) {
    return new HtmlElementImpl("li", htmlRenderable);
  }

  public static HtmlElement element(String tag, HtmlElement... htmlElements) {
    return new HtmlElementImpl(tag, htmlElements);
  }

  public static HtmlElement text(String text) {
    return new Text(text);
  }

  public static String render(HtmlRenderable htmlRenderable) {
    try {
      StringBuilder stringBuilder = new StringBuilder();
      htmlRenderable.renderToWriter(stringBuilder);
      return stringBuilder.toString();
    } catch (IOException i) {
      throw new RuntimeException(i);
    }
  }

  public static final HtmlElement br = new HtmlElementImpl("br");

  public static HtmlElement br() {
    return br;
  }

  public static HtmlAttribute href(String href) {
    return new HtmlAttributeImpl("href", href);
  }

  public static HtmlAttribute className(String className) {
    return new HtmlAttributeImpl("class", className);
  }

  public static HtmlAttribute attr(String key, String value) {
    return new HtmlAttributeImpl(key, value);
  }

  public static HtmlAttribute [] attributes(HtmlAttribute... htmlAttributes) {
    return htmlAttributes;
  }

  //////////////////////////////////////////////////////////////////////////////
  // Interfaces
  /////////////////////////////////////////////////////////////////////////////
  public interface HtmlRenderable {
    void renderToWriter(Appendable appendable) throws IOException;
  }

  public static interface HtmlElement extends HtmlRenderable {
  }

  public static interface HtmlAttribute extends HtmlRenderable {
  }

  //////////////////////////////////////////////////////////////////////////////
  // Implementation
  //////////////////////////////////////////////////////////////////////////////
  public static class HtmlAttributeImpl implements HtmlAttribute {
    
    private static final String END_HYPEN = "\"";
    private static final String EQUALS_WITH_OPENING_HYPEN = "=\"";

    private final String name;
    private final String value;

    //private static final String NAME_VALUE_WITH_PLACEHOLDER = "%s=\"%s\"".intern();

    public HtmlAttributeImpl(String name, String value) {
      this.name = name;
      this.value = value;
    }

    @Override
    public void renderToWriter(Appendable writer) throws IOException {
      writer.append(name);
      writer.append(EQUALS_WITH_OPENING_HYPEN);
      writer.append(value);
      writer.append(END_HYPEN);
      //writer.append(String.format(NAME_VALUE_WITH_PLACEHOLDER, name, value));
    }
  }

  public static class Document implements HtmlElement {

    private final HtmlElement docType;
    private final HtmlElement html;

    public Document(HtmlElement docType, HtmlElement html) {
      this.docType = docType;
      this.html = html;
    }

    @Override
    public void renderToWriter(Appendable writer) throws IOException {
      docType.renderToWriter(writer);
      html.renderToWriter(writer);
    }
  }

  public static class Text implements HtmlElement {

    private final String text;

    public Text(String text) {
      this.text = text;
    }

    @Override
    public void renderToWriter(Appendable writer) throws IOException {
      writer.append(text);
    }
  }

  public static class HtmlElementImpl implements HtmlElement {

    private final String tag; // br, div, a etc
    private final HtmlElement [] htmlElements;
    private final HtmlAttribute [] htmlAttributes;

    // this is more "efficient" as we don't want to create the strings
    // over and over again... hmm. is it really? => to be verified...
    private static final String SPACE = " ";
    private static final String OPENING_BRACE = "<";
    private static final String OPENING_BRACE_CLOSING_TAG = "</";
    private static final String CLOSING_BRACE_EMPT = "/>";
    private static final String CLOSING_BRACE = ">";

    private HtmlElementImpl(String tag, HtmlAttribute [] htmlAttributes, HtmlElement ... htmlElements) {
      this.tag = tag;
      this.htmlElements = htmlElements;
      this.htmlAttributes = htmlAttributes;
    }

    private HtmlElementImpl(String htmlElementName, HtmlElement... htmlElements) {
      this(htmlElementName, new HtmlAttribute[0], htmlElements);
    }
    
    private HtmlElementImpl(String htmlElementName, HtmlAttribute ... htmlAttributes) {
      this(htmlElementName, htmlAttributes, new HtmlElement[0]);
    }

     private HtmlElementImpl(String htmlElementName) {
      this(htmlElementName,  new HtmlAttribute[0], new HtmlElement[0]);
    }
        
    //private HtmlElementImpl(String htmlElementName, HtmlAttribute [] htmlAttributes, HtmlElement... htmlElements) {
    //  this(htmlElementName, htmlAttributes, htmlElements);
    //}

    // ==> https://www.owasp.org/index.php/XSS_(Cross_Site_Scripting)_Prevention_Cheat_Sheet
//    public String escapeUntrustedHtmlElementContent(String untrustedString) {
//      return untrustedString
//        .replaceAll("&", "&amp;")
//        .replaceAll("<", "&lt;")
//        .replaceAll(">", "&gt;")
//        .replaceAll("\"", "&quot;")
//        .replaceAll("'", "&#x27;")
//        .replaceAll("/", "&#x2F;");
//    }

    @Override
    public void renderToWriter(Appendable writer) throws IOException {
      writer.append(OPENING_BRACE);
      writer.append(tag);
      if (htmlAttributes.length == 0) {
        // don't add anything...
      } else {
        for (HtmlRenderable htmlAttribute : htmlAttributes) {
          writer.append(SPACE);
          htmlAttribute.renderToWriter(writer);
        }
      }

      // if we don't have any inner html, we close the tag
      if (htmlElements.length == 0) {
        writer.append(CLOSING_BRACE_EMPT);
      } else {
        writer.append(CLOSING_BRACE);

        for (HtmlElement htmlElement : htmlElements) {
          htmlElement.renderToWriter(writer);
        }

        writer.append(OPENING_BRACE_CLOSING_TAG);
        writer.append(tag);
        writer.append(CLOSING_BRACE);
        
      }

    }

  }

}
