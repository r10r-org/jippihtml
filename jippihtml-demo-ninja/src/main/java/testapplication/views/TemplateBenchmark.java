package testapplication.views;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import testapplication.views.layout.LayoutHtml;
import static org.r10r.jippihtml.JippiHtml.*;
import testapplication.controllers.Stock;

/**
 * That's a main template a user would use.
 */
public class TemplateBenchmark {
  
  static String css = "\n" +
"		body {\n" +
"			color: #333333;\n" +
"			line-height: 150%;\n" +
"		}\n" +
"		thead {\n" +
"			font-weight: bold;\n" +
"			background-color: #CCCCCC;\n" +
"		}\n" +
"		.odd {\n" +
"			background-color: #FFCCCC;\n" +
"		}\n" +
"		.even {\n" +
"			background-color: #CCCCFF;\n" +
"		}\n" +
"		.minus {\n" +
"			color: #FF0000;\n" +
"		}\n";   
    public static HtmlElement template(List<Stock> stocks) {
      
      //doctypeHtml()
        
        return 
          document(
            doctypeHtml(),

            html(
            head(
              title("Stock Prices"),
              meta(attr("http-equiv", "Content-Type"), attr("content", "text/html; charset=UTF-8")),
              meta(attr("http-equiv", "Content-Style-Type"), attr("content", "text/css")),
              meta(attr("http-equiv", "Content-Script-Type"), attr("content", "text/javascript")),
              link(attr("rel", "shortcut icon"), href("/images/favicon.ico")),
              link(attr("rel", "stylesheet"), attr("type", "text/css"), href("/css/style.css"), attr("media", "all")),
              //script(attr("type", "text/javascript"), href("/js/util.js")),
              style(
                attributes(attr("style", "text/css")),
                css
                )
            ),
            body(
              h1("Stock prices"),
              table(
                thead(
                  tr(
                    th("#"),
                    th("symbol"),
                    th("name"),
                    th("price"),
                    th("change"),
                    th("ratio")
                  )
                ),
                tbody(
                  IntStream.range(0, stocks.size()).mapToObj(i -> {
                    Stock stock = stocks.get(i);

                    return
                      tr(
                        attributes(( (i % 2 == 0) ? className("even") : className("odd"))),
                        td(i + 1 + ""),
                        td(
                          a(attributes(href("/stocks/" + stock.symbol)),
                            text(stock.symbol)
                          )
                        ),
                        td(
                          a(attributes(href(stock.url)),
                            text(stock.name)
                          )
                        ),
                        td(
                          strong(stock.price + "")
                        ),
                        (stock.change < 0.0) ? td(attributes(className("minus")), text(stock.change + "")) : td(text(stock.change + "")),
                        (stock.change < 0.0) ? td(attributes(className("minus")), text(stock.ratio + "")) : td(text(stock.ratio + ""))
                      );
                    }).collect(Collectors.toList())
                  )
                )
              )
            )
          ); 
    }
      /*
      
      <!DOCTYPE html>
<html>
<head>
	<title>Stock Prices</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="Content-Style-Type" content="text/css" />
	<meta http-equiv="Content-Script-Type" content="text/javascript" />
	<link rel="shortcut icon" href="/images/favicon.ico" />
	<link rel="stylesheet" type="text/css" href="/css/style.css" media="all" />
	<script type="text/javascript" src="/js/util.js"></script>
	<style type="text/css">
		
	</style>

</head>

	<body>

		<h1>Stock Prices</h1>

		<table>
	   		<thead>
	    		<tr>
	     			<th>#</th>
	     			<th>symbol</th>
	     			<th>name</th>
	     			<th>price</th>
	     			<th>change</th>
	     			<th>ratio</th>
	    		</tr>
			</thead>
			<tbody>
			{% for item in items %}
				<tr class="{% if (loop.index + 1) is even %}even{% else %}odd{% endif %}">
			    	<td>{{loop.index + 1}}</td>
			    	<td>
			    		<a href="/stocks/{{ item.symbol }}">{{item.symbol}}</a>
			    	</td>
			     	<td>
			     		<a href="{{ item.url }}">{{item.name}}</a>
			     	</td>
			     	<td>
			     		<strong>{{ item.price }}</strong>
			     	</td>

					{% if item.change < 0.0 %}
						<td class="minus">{{item.change}}</td>
						<td class="minus">{{item.ratio}}</td>
					{% else %}
						<td>{{item.change}}</td>
						<td>{{item.ratio}}</td>
					{% endif %}

				</tr>
			{% endfor %}
			</tbody>
		</table>
	</body>
</html>
        
        
      */
      
        
        
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
