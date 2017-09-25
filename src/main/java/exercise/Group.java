package exercise;


/*
Simple class that matches the information scrabed from CA-1 hand-ins
Meant to be used as input to a gson converter
*/
public class Group {
  String url;
  String authors;
  String theClass;
  String group;
  String error;

  public Group(String url, String error) {
    this.url = url;
    this.error = error;
  }

  public Group(String url, String authors, String theClass, String group) {
    this.url = url;
    this.authors = authors;
    this.theClass = theClass;
    this.group = group;
  }


  @Override
  public String toString() {
    return "Group{" + "url=" + url + ", authors=" + authors + ", theClass=" + theClass + ", group=" + group + '}';
  }
  
  
}