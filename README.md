# Jippihtml - a Java html rendering engine that does not suck [![Build Status](https://api.travis-ci.org/r10r-org/jippihtml.svg)](https://travis-ci.org/r10r-org/jippihtml)

# Intro

Templating engines in Java are usually quite separated from the Java compile
process. Some use plain text files that are being read (Freemarker eg), others
use plugins to compile text files to source code that are then basically Java
classes (eg https://github.com/fizzed/rocker).

Each approach has pros and cons.

The goal of this experiment is to create a templating engine that does not need
any parsing or code generation, but still is easy to use in your IDE and can
use stuff like dependency injection and so on.

Because Java still does not have multi-line strings we cannot simply use
a class with a long static method that creates a string. It must be something else.

# Goals

 - Java Template engine focusing on html rendering 
 - 100% Java based. No need to learn anything special. Just use
   what fits the job. Iterate via streams api. Use your own converters. Easy.
 - Zero dependencies
 - "Compile time safe". Can be used like any other Java class.

# Non goals

 - Jippihtml will NOT necessarily create valid html. Not a goal.
 - html syntax highlighting won't work. Not a goal.

# How does it looks like ?

Jippihtml is based around two concepts: Elements and attributes. Elements can
contain other elements, and attributes are part of elements.

That's it already. Two interfaces, two implementations. Done.

To make this easily usable we got a fair amount of syntactic sugar that
allows to write html files using a Java based dsl.

# Some code

A common usecase for web applications is rendering an index page that is embeddeded
inside a template (header, footer etc).

In JippiHtml this'd look like that:

```java
// Don't forget to import JippiHtml statically to get all syntactic sugar
import static org.r10r.jippihtml.JippiHtml.*;

// Organize your templates as you want. One file. Different files.
// At the end it's just a method you'll call.
public class Templates {
  public HtmlElement siteTemplate(HtmlElement... mainPage) {
    return 
      html(
        head(
          title("My awesome website")
        ),
        body(
          mainPage
        )
      );
  }

  public HtmlElement indexPage() {
    return 
      siteTemplate(
        h1("This is my index page"),
        div(attributes(className("main")),
          text("this is the main page")
        )
      );
  }

  public void renderPage() {
    // render(...) creates a string, but it is more performant to write
    // directly to an output stream in a webserver scenario.
    String renderedPage = render(indexPage());

    // ....
  }
}
 ```

Notice that the methods return HtmlElement. HtmlElements are list of strings
and can be rendered efficiently on outputsreams of web servers.


renderedPage will be the following plain text html:

```html
<html>

<head>
    <title>My awesome website</title>
</head>

<body>
    <h1>This is my index page</h1>
    <div class="main">this is the main page</div>
</body>

</html>
```

# TODO

- Examine whether stream api aka List<> vs [] has impact on performance
- Release performance microbenchmark
- Examine whether .inter() has any performance impact at all.








 

