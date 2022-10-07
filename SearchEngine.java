import java.io.IOException;
import java.net.URI;
import java.util.*;

class SearchHandler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> words = new ArrayList<String>();

    public String handleRequest(URI url) {
        System.out.println("Path: " + url.getPath());
        if (url.getPath().equals("/")) {
            return String.format("Words: %d", words);
        } 
        else if (url.getPath().contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if (parameters[0].equals("word")) {
                words.add(parameters[1]);
                return String.format("Word %s added", parameters[1]);
            }
            return "404 Not Found!";
        }
        else if (url.getPath().contains("/search")){
            System.out.println("Path: " + url.getPath());
            String[] parameters = url.getQuery().split("=");
            int i = 0;
            if (parameters[0].equals("word")) {
                for (i = 1; i < words.size(); i++){
                    if (words.get(i).contains(parameters[1])){
                        return String.format(parameters[1]);
                    }
                }
            //  return String.format("Number increased by %s! It's now %d", parameters[1], num);
            }
            return "404 Not Found!";
        }
    }
}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}