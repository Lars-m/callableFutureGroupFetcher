package exercise;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GroupFetcher {

  
  public static List<String> urls = new ArrayList<String>() {
    {
      add("http://165.227.151.92:8080/CA1/documentation.html");
      add("http://46.101.216.31/CA1-Group13/");
      add("http://46.101.138.20:8080/CA1_Group4-1.0-SNAPSHOT/");
      add("http://207.154.220.147/company/");
      add("http://207.154.229.78:8080/CA1-1.0-SNAPSHOT/");
      add("http://138.197.177.21/CourseAssignment1-1.0-SNAPSHOT/");
      add("http://www.alfen.me/CA1/");
    }
  };
  
  public static String fetchGroups(List<String> urls) throws InterruptedException, ExecutionException{
   
    //Create a list of futures (same size as list of urls) 
    List<Future<Group>> futures = new ArrayList<>(urls.size());
    
    //Create a list of Groups, which eventually will hold a Group instance for each URL, ready to be converted into JSON
    List<Group> groups = new ArrayList();
    
    //Create a ThreadPool for the exercise
    final ExecutorService executor = Executors.newFixedThreadPool(20);

    //Start all the Fetchers (Callables). This will make Fechers execute in a number of separate Threads
    //Because of UI blocking (mainly) and because of multi-kernel CPU's this should give a noticeable performance boost
    for (String url : urls) {
      futures.add(executor.submit(new Fetcher(url)));
    }
    executor.shutdown();

    //Wait (blocking) for each result. Observer that we get results in the order they were started 
    for (Future<Group> future : futures) {
      Group g = future.get();
      groups.add(g);
    }
    
    //Transform to JSON and return result
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(groups);
    return json;
  }

  public static void main(String[] args) throws InterruptedException, ExecutionException {

    
    System.out.println(fetchGroups(urls));

  }

}
