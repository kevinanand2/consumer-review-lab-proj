import java.io.*;
import java.util.*;

public class SentimentAnalysisJoker {
    public static Set<String> loadWordsFromFile(String fileName) throws IOException {
        Set<String> words = new HashSet<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            words.add(line.trim().toLowerCase());  //adding each word to the set 
        }
        reader.close();
        return words;
    }
    public static int calculateSentimentScore(String review, Set<String> positiveWords, Set<String> negativeWords) {
        int score = 0;
        String[] words = review.toLowerCase().split("\\W+");

        for (String word : words) {
            if (positiveWords.contains(word)) {
                score++; 
            } else if (negativeWords.contains(word)) {
                score--; 
            }
        }
        return score;
    }

    // Method to analyze a review and print if it's positive, negative, or neutral
    public static void analyzeReview(String review, Set<String> positiveWords, Set<String> negativeWords) {
        int score = calculateSentimentScore(review, positiveWords, negativeWords);

        // conditionals
        if (score > 0) {
            System.out.println("Positive review: " + review);
        } else if (score < 0) {
            System.out.println("Negative review: " + review);
        } else {
            System.out.println("Neutral review: " + review);
        }
    }

    public static void main(String[] args) {
        try {
            // loading neg & pos words 
            Set<String> positiveWords = loadWordsFromFile("positiveAdjectives.txt");
            Set<String> negativeWords = loadWordsFromFile("negativeAdjectives.txt.txt");

           // reviews (fake as of right now, changing)
            String[] reviews = {
                "Joker was an amazing film. Absolutely brilliant performance by Joaquin Phoenix.",
                "I found the movie boring and quite disappointing. It failed to meet my expectations.",
                "What a masterpiece! The direction and storytelling were fantastic.",
                "The movie was okay, but I wouldn't call it great. Some parts were dull.",
                "Hated the movie. It was one of the worst films I've ever seen.",
                "Superb acting, but the plot was a bit weak. Overall, a decent watch.",
                "Loved it! The Joker was excellent, and the atmosphere was so intense.",
                "This film was terrible. I regretted watching it."
            };

            // Analyze each review in the sample data
            for (String review : reviews) {
                analyzeReview(review, positiveWords, negativeWords);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while reading the adjective files.");
            e.printStackTrace();
        }
    }
}
