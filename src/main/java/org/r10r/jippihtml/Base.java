package org.r10r.jippihtml;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Base {

  //////////////////////////////////////////////////////////////////////////////
  // utility for crisp source code
  public static HtmlElement html(HtmlRenderable... htmlRenderable) {
    return new HtmlElementImpl("html", htmlRenderable);
  }

  public static HtmlElement title(String text) {
    return new HtmlElementImpl("title", text(text));
  }

  public static HtmlElement body(HtmlRenderable... htmlRenderable) {
    return new HtmlElementImpl("body", htmlRenderable);
  }

  public static HtmlElement head(HtmlRenderable... htmlRenderable) {
    return new HtmlElementImpl("head", htmlRenderable);
  }

  public static HtmlElement div(HtmlRenderable... htmlRenderable) {
    return new HtmlElementImpl("div", htmlRenderable);
  }

  public static HtmlElement a(HtmlRenderable... htmlRenderable) {
    return new HtmlElementImpl("a", htmlRenderable);
  }

  public static HtmlElement text(String text) {
    return new Text(text);
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

  //////////////////////////////////////////////////////////////////////////////
  // Interfaces
  public static interface HtmlRenderable {

    String render();
  }

  public static interface HtmlElement extends HtmlRenderable {
  }

  public static interface HtmlAttribute extends HtmlRenderable {
  }

  //////////////////////////////////////////////////////////////////////////////
  // Implementation
  public static class HtmlAttributeImpl implements HtmlAttribute {

    private final String key;
    private final String value;

    public HtmlAttributeImpl(String key, String value) {
      this.key = key;
      this.value = value;
    }

    @Override
    public String render() {
      return String.format("%s=\"%s\"", key, value);
    }
  }

  public static class Text implements HtmlElement {

    private String text;

    public Text(String text) {
      this.text = text;
    }

    @Override
    public String render() {
      return text;
    }
  }

  public static class HtmlElementImpl implements HtmlElement {

    private final String htmlElementName; // br, div, a etc
    private final HtmlRenderable[] renderables;

    private HtmlElementImpl(String htmlElementName, HtmlRenderable... renderables) {
      this.htmlElementName = htmlElementName;
      this.renderables = renderables;
    }

    public static HtmlElement htmlElement(String htmlElementName,
      HtmlRenderable... renderables) {
      return new HtmlElementImpl(htmlElementName, renderables);
    }

    @Override
    public String render() {
      // attributes
      List<HtmlRenderable> htmlAttributes
        = Stream.of(renderables).filter(c -> c instanceof HtmlAttribute).collect(Collectors.toList());
      String htmlAttributesRendered = htmlAttributes.stream().map(c
        -> c.render()).collect(Collectors.joining(" "));

      // htmlelements => go to inner
      List<HtmlRenderable> htmlElements
        = Stream.of(renderables).filter(c -> c instanceof HtmlElement).collect(Collectors.toList());
      String htmlElementsRendered = htmlElements.stream().map(c
        -> c.render()).collect(Collectors.joining(""));

      String htmlStartTagWithAttributes;
      if (htmlAttributesRendered.equals("")) {
        // no space between attribute names and tag...2
        htmlStartTagWithAttributes = String.format("%s", htmlElementName);
      } else {
        // with space between attribute name and attributes
        htmlStartTagWithAttributes = String.format("%s %s",
          htmlElementName, htmlAttributesRendered);
      }

      // if we don't have any inner html, we close the tag
      String fullHtmlRendered;
      if (htmlElementsRendered.equals("")) {
        fullHtmlRendered = String.format("<%s/>", htmlStartTagWithAttributes);
      } else {
        fullHtmlRendered = String.format("<%s>%s</%s>",
          htmlStartTagWithAttributes, htmlElementsRendered, htmlElementName);
      }

      return fullHtmlRendered;
    }

  }

}
