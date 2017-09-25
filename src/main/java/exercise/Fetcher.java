package exercise;

import java.io.IOException;
import java.util.concurrent.Callable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/*
Callables that fetches, Authors, Class and Group from a CA-1 hand-in page
Returns the result encapsulated in a Group object
*/
public class Fetcher implements Callable<Group> {

  private final String url;

  public Fetcher(String url) {
    this.url = url;
  }

  @Override
  public Group call() throws Exception {
    Document doc;
    try {
      doc = Jsoup.connect(url).get();
    } catch (IOException ex) {
      return new Group(url, "Connection Error");
    }

    // Elements newsHeadlines = doc.select(".group");
    Elements authors = doc.select("#authors");
    Elements classs = doc.select("#class");
    Elements group = doc.select("#group");
    if (authors.isEmpty() && classs.isEmpty() && group.isEmpty()) {
      return new Group(url, "No Group Info Found");
    }

    return new Group(url, authors.text(), classs.text(), group.text());
  }

}
