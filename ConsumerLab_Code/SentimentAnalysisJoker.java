import java.io.*;
import java.util.*;

public class SentimentAnalysisJoker {

    //method to load words from a file into a map (word -> sentiment score)
    public static Map<String, Double> loadWordsFromFile(String fileName) throws IOException {
        Map<String, Double> words = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split(",");  // Split word and its score by comma
            if (parts.length == 2) {
                String word = parts[0].toLowerCase();
                double score = Double.parseDouble(parts[1]);
                words.put(word, score);  // Add word and its score to the map
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
                score += positiveWords.get(word);  // Add positive word score
                System.out.println("Positive match: " + word + " (Score: " + positiveWords.get(word) + ")");  // Debugging output
            } else if (negativeWords.containsKey(word)) {
                score += negativeWords.get(word);  // Add negative word score
                System.out.println("Negative match: " + word + " (Score: " + negativeWords.get(word) + ")");  // Debugging output
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
                "I found the movie boring and quite disappointing. It failed to meet my expectations.",
                "What a masterpiece! The direction and storytelling were fantastic.",
                "The movie was okay, but I wouldn't call it great. Some parts were dull.",
                "Hated the movie. It was one of the worst films I've ever seen.",
                "Superb acting, but the plot was a bit weak. Overall, a decent watch.",
                "Loved it! The Joker was excellent, and the atmosphere was so intense.",
                "This film was terrible. I regretted watching it.",
                "generation, generics"
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
