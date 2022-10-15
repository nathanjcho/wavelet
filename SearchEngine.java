import java.io.IOException;
import java.net.URI;
import java.util.*;

class SearchHandler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    ArrayList<String> words = new ArrayList<String>();
    ArrayList<String> selectedWords = new ArrayList<String>();

    int numOfWords = 0;

    public String handleRequest(URI url) {
        System.out.println("Path: " + url.getPath());
        if (url.getPath().equals("/")) {
            return String.format("Number of Strings: %d", numOfWords);
        } 
        else if (url.getPath().contains("/add")) { 
            String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    String newWord = parameters[1];
                    words.add((newWord));
                    numOfWords += 1;
                    return String.format("Word %d added. Now we have %s words", newWord, numOfWords);
                }
            return String.format("Error. Type in a correct format");
        } 
        else if (url.getPath().contains("/search")) {
            System.out.println("Path: " + url.getPath());
            String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    String searchedWord = parameters[1];
                    for (String s : words){
                        if (words.contains(searchedWord)){
                           selectedWords.add(searchedWord);
                        }
                    }
                    if (selectedWords.size() == 0) {
                        return String.format("There are no words in the list that contain %d", searchedWord);
                    }
                    else {
                        return selectedWords.toString();
                    }
                }
            return ("Words found");
        } 
        else {
            System.out.println("Path: " + url.getPath());
            return "404 Not Found";
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