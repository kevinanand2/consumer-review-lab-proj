import java.io.*;
import java.util.*;

public class SentimentAnalysisJoker {

    //method to load words from a file into a map (word -> sentiment score)
    public static Map<String, Double> loadWordsFromFile(String fileName) throws IOException {
        Map<String, Double> words = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split(",");  //split word and its score by comma
            if (parts.length == 2) {
                String word = parts[0].toLowerCase();
                double score = Double.parseDouble(parts[1]);
                words.put(word, score);  //add word and its score to the map
            }
        }
        reader.close();
        return words;
    }

    //method to calculate sentiment score of a review
    public static double calculateSentimentScore(String review, Map<String, Double> positiveWords, Map<String, Double> negativeWords) {
        double score = 0;
        // splitting words and removing punctuation
        String[] words = review.toLowerCase().split("\\W+");

        for (String word : words) {
            word = word.trim();  // clean any extra spaces

            if (positiveWords.containsKey(word)) {
                score += positiveWords.get(word);  // adds pos word score 
                System.out.println("Positive match: " + word + " (Score: " + positiveWords.get(word) + ")"); 
            } else if (negativeWords.containsKey(word)) {
                score += negativeWords.get(word);  // adds neg word score 
                System.out.println("Negative match: " + word + " (Score: " + negativeWords.get(word) + ")");  
            }
        }
        return score;
    }

    //method to analyze a review and print if it's positive or negative
    public static void analyzeReview(String review, Map<String, Double> positiveWords, Map<String, Double> negativeWords) {
        double score = calculateSentimentScore(review, positiveWords, negativeWords);

        //conditionals printing out if it's a positive or negative review
        if (score > 1) {
            System.out.println("Positive review: " + review);
        } else {
            System.out.println("Negative review: " + review);
        }
    }

    public static void main(String[] args) {
        //the file paths
        String positiveWordsFile = "positiveAdjectives.txt";
        String negativeWordsFile = "negativeAdjectives.txt";

        try {
            //loading words from adjectives with scores
            Map<String, Double> positiveWords = loadWordsFromFile(positiveWordsFile);
            Map<String, Double> negativeWords = loadWordsFromFile(negativeWordsFile);

            // reviews (fake as of right now, changing)
            String[] reviews = {
                "Why was it a musical. Terrible story line. The only thing good about it was the trials and even that was boring.",
                "All I can say is no. One of the worst movies I’ve ever seen, walked out before it was over. Could not wait for it to end.",
                "This movie was pointless. It feels like the director was making an apology movie saying sorry for the first one. The first movie was about the mental health of an abused man who was bullied untill he snapped in the most horrific ways. It was a dark and sad movie. This second movie is basically a lady gaga movie based in Gotham. The music was not original and did not progress the movie. It stopped the movie so gaga could sing with Phoenix. This movie is trash. If you liked the first you're probably not going to like this garbage unoriginal musical.",
                "I thought the previous Joker movie was truly great. I thought this movie was a real let down. Purely because it was a musical… I felt the movie tried hard to be “art”, which perhaps it is! But I wished it was just a movie, building on the previous joker…",
                "FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!NFE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N FE!N",
                "Really!!! This movie was a JOKE! Popcorn was great!!",
                "This was such a disappointmemnt after really enjoying the first film.",
                "I thought that this movie is good. People complain about the musical thing, but they don’t get the spirit of it. The beginning of the movie shows exactly what’s the movie about. It’s about Arthur Fleck. How he really is. It shows that the Joker is a persona that takes over him. It’s not a movie about Batman and Joker… well, for me it was worth watching it.",
                "It's a great movie, it just panders to the wrong audience by using a character for man children who expect comic book shenanigans",
                "same dark theme and really well made. movie had a lot of musical moments, and Lady Gaga was the perfect choice. saw some people leaving the theater so we're disappointed that the sequel was not a great as the first.",
                "bad bad bad. disgrace to the joker"
            };

            // looping thru & analyzing each review 
            for (String review : reviews) {
                analyzeReview(review, positiveWords, negativeWords);
                System.out.println();
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the adjective files.");
            e.printStackTrace();
        }
    }
}
