package org.r10r.jippihtml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Base {
  
  //////////////////////////////////////////////////////////////////////////////
  // utility for crisp source code
  public static HtmlElement html(List<HtmlAttribute> htmlAttributes, HtmlElement... htmlElements) {
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
  public static HtmlElement div(List<HtmlAttribute> htmlAttributes, HtmlElement... htmlElements) {
     return new HtmlElementImpl("div", htmlAttributes, htmlElements);
  }
  public static HtmlElement a(List<HtmlAttribute> htmlAttributes, String text) {
     return new HtmlElementImpl("a", htmlAttributes, text(text));
  }
  public static HtmlElement a(List<HtmlAttribute> htmlAttributes, HtmlElement... htmlElements) {
     return new HtmlElementImpl("a", htmlAttributes, htmlElements);
  }
  public static HtmlElement a(HtmlElement... htmlElements) {
     return new HtmlElementImpl("a", htmlElements);
  }
  public static HtmlElement ul(HtmlElement... htmlElements) {
     return new HtmlElementImpl("ul", htmlElements);
  }
  public static HtmlElement ul(List<HtmlElement> htmlElements) {
     return new HtmlElementImpl("ul", htmlElements);
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
    StringBuilder stringBuilder = new StringBuilder();
    for (String s : htmlRenderable.renderAsArrayOfStrings()) {
      stringBuilder.append(s);
    }
    return stringBuilder.toString();
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
  
  public static List<HtmlAttribute> attributes(HtmlAttribute ... htmlAttributes) {
     return Arrays.asList(htmlAttributes);
  }
  
  //////////////////////////////////////////////////////////////////////////////
  // Interfaces
  public interface HtmlRenderable {
    // Just a way to efficiently connect the strings later on.
    List<String> renderAsArrayOfStrings();
  }
  
  public static interface HtmlElement extends HtmlRenderable {}
    
  public static interface HtmlAttribute extends HtmlRenderable {}
  
  
  //////////////////////////////////////////////////////////////////////////////
  // Implementation
  public static class HtmlAttributeImpl implements HtmlAttribute {
    private final String name;
    private final String value;
    
    private static final String NAME_VALUE_WITH_PLACEHOLDER = "%s=\"%s\"".intern();
    
    public HtmlAttributeImpl(String name, String value) {
      this.name = name;
      this.value = value;
    }

    @Override
    public List<String> renderAsArrayOfStrings() {
      return Collections.singletonList(String.format(NAME_VALUE_WITH_PLACEHOLDER, name, value));
    }
  }
  
  public static class Text implements HtmlElement {
    private final String text;

    public Text(String text) {
      this.text = text;
    }

    @Override
    public List<String> renderAsArrayOfStrings() {
      return Collections.singletonList(text);
    }
  }
  
  public static class HtmlElementImpl implements HtmlElement {
    
    private final String tag; // br, div, a etc
    private final List<HtmlElement> htmlElements;
    private final List<HtmlAttribute> htmlAttributes;
    
    // this is more "efficient" as we don't want to create the strings
    // over and over again... hmm. is it really? => to be verified...
    private static final String SPACE = " ".intern();
    private static final String OPENING_BRACE = "<".intern();
    private static final String CLOSING_BRACE_NO_CONTENT = "/>".intern();
    private static final String CLOSING_BRACE = ">".intern();
    private static final String CLOSING_TAG_WITH_PLACEHOLDER = "</%s>".intern();
    
    private HtmlElementImpl(String tag, List<HtmlAttribute> htmlAttributes, List<HtmlElement> htmlElements) {
      this.tag = tag;
      this.htmlElements = htmlElements;
      this.htmlAttributes = htmlAttributes;
    }
    
    private HtmlElementImpl(String htmlElementName, HtmlElement ... htmlElements) {
      this(htmlElementName, Collections.EMPTY_LIST, Arrays.asList(htmlElements));
    }
    
    private HtmlElementImpl(String htmlElementName, List<HtmlElement> htmlElements) {
      this(htmlElementName, Collections.EMPTY_LIST, htmlElements);
    }
        
    private HtmlElementImpl(String htmlElementName, List<HtmlAttribute> htmlAttributes, HtmlElement... htmlElements) {
      this(htmlElementName, htmlAttributes, Arrays.asList(htmlElements));
    }

    @Override
    public List<String> renderAsArrayOfStrings() {

      List<String> fullHtml = new ArrayList<>();
      fullHtml.add(OPENING_BRACE);
      fullHtml.add(tag);
      if (htmlAttributes.isEmpty()) {
        // don't add anything...
      } else {
        for (HtmlRenderable htmlAttribute: htmlAttributes) {
          // fixme...
          fullHtml.add(SPACE);
          fullHtml.addAll(htmlAttribute.renderAsArrayOfStrings());
        }
      }
      
      // if we don't have any inner html, we close the tag
      if (htmlElements.isEmpty()) {
        fullHtml.add(CLOSING_BRACE_NO_CONTENT);
      } else {
        fullHtml.add(CLOSING_BRACE);
        
        for (HtmlElement htmlElement: htmlElements) {
          fullHtml.addAll(htmlElement.renderAsArrayOfStrings());
        }
        
        fullHtml.add(String.format(CLOSING_TAG_WITH_PLACEHOLDER, tag));
      }
        
      return fullHtml;
    }
    
      // ==> https://www.owasp.org/index.php/XSS_(Cross_Site_Scripting)_Prevention_Cheat_Sheet
    public String escapeUntrustedHtmlElementContent(String untrustedString) {
      return untrustedString
        .replaceAll("&", "&amp;")
        .replaceAll("<", "&lt;")
        .replaceAll(">", "&gt;")
        .replaceAll("\"", "&quot;")
        .replaceAll("'", "&#x27;")
        .replaceAll("/", "&#x2F;");
    }

  }

}

