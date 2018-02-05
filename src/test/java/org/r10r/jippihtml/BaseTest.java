package org.r10r.jippihtml;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.r10r.jippihtml.Base.*;
import org.hamcrest.CoreMatchers;

public class BaseTest {

  public BaseTest() {
  }
  
  // That's a site template, where you can include the variable parts
  private HtmlElement siteTemplate(HtmlElement... mainPage) {
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

  @Test
  public void testSomeMethod() {

    HtmlElement fullPage = 
      siteTemplate(
        div(
            className(".rectangle"),
            a(href("http://myWebservercom"), className(".button-id"),
              attr("id", "myId"), text("firstlink")),
            br(),
            a(href("http://mywebserver2.com"),
              className(".button-two"), text("secondlinkg"))
          ),
          div(
            className("one"),
            text("a text")
          )
      );

    String renderedHtmlElement = fullPage.render();

    assertThat(renderedHtmlElement,
      CoreMatchers.equalTo("<html><head><title>My awesome website</title></head><body><div class=\".rectangle\"><a href=\"http://myWebservercom\" class=\".button-id\" id=\"myId\">firstlink</a><br/><a href=\"http://mywebserver2.com\" class=\".button-two\">secondlinkg</a></div><div class=\"one\">a text</div></body></html>"));
  }

}
/*
  Expected: "<html><head><title>My awesomewebsite</title ></head><body><div class=\".rectangle\"><a href=\"http://myWebservercom\" class=\".button-id\" id=\"myId\">firstlink</a><br/><a href=\"http://mywebserver2.com\" class=\".button-two\">secondlinkg</a></div><div class=\"one\">a text</div></body></html>"      but: was "<html><head><title>My awesome website</title></head><body><div class=\".rectangle\"><a href=\"http://myWebservercom\" class=\".button-id\" id=\"myId\">firstlink</a><br/><a href=\"http://mywebserver2.com\" class=\".button-two\">secondlinkg</a></div><div class=\"one\">a text</div></body></html>"
java.lang.AssertionError
Expected: "    <html><head><title>My awesomew ebsite</title ></head><body><div class=\".rectangle\"><a href=\"http://myWebservercom\" class=\".button-id\" id=\"myId\">firstlink</a><br/><a href=\"http://mywebserver2.com\" class=\".button-two\">secondlinkg</a></div><div class=\"one\">a text</div></body></html>"
     but: was "<html><head><title>My awesome website</title></head><body><div class=\".rectangle\"><a href=\"http://myWebservercom\" class=\".button-id\" id=\"myId\">firstlink</a><br/><a href=\"http://mywebserver2.com\" class=\".button-two\">secondlinkg</a></div><div class=\"one\">a text</div></body></html>"
	at org.hamcrest.MatcherAssert.assertThat(MatcherAssert.java:20)
	at org.junit.Assert.assertThat(Assert.java:956)
	at org.junit.Assert.assertThat(Assert.java:923)
	at org.r10r.jippihtml.BaseTest.testSomeMethod(BaseTest.java:39)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:50)
	at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:12)
	at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:47)
	at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:17)
	at org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:78)
	at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:57)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.apache.maven.surefire.junit4.JUnit4TestSet.execute(JUnit4TestSet.java:53)
	at org.apache.maven.surefire.junit4.JUnit4Provider.executeTestSet(JUnit4Provider.java:123)
	at org.apache.maven.surefire.junit4.JUnit4Provider.invoke(JUnit4Provider.java:104)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at org.apache.maven.surefire.util.ReflectionUtils.invokeMethodWithArray(ReflectionUtils.java:164)
	at org.apache.maven.surefire.booter.ProviderFactory$ProviderProxy.invoke(ProviderFactory.java:110)
	at org.apache.maven.surefire.booter.SurefireStarter.invokeProvider(SurefireStarter.java:175)
	at org.apache.maven.surefire.booter.SurefireStarter.runSuitesInProcessWhenForked(SurefireStarter.java:107)
	at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:68)

*/