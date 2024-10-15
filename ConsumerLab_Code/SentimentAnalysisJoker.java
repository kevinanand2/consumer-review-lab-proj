import java.io.*;
import java.util.*;

public class SentimentAnalysisJoker {

    
    public static Map<String, Double> loadWordsFromFile(String fileName) throws IOException {
        Map<String, Double> words = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.trim().split(","); 
            if (parts.length == 2) {
                String word = parts[0].toLowerCase();
                double score = Double.parseDouble(parts[1]);
                words.put(word, score); 
            }
        }
        reader.close();
        return words;
    }

    
    public static double calculateSentimentScore(String review, Map<String, Double> positiveWords, Map<String, Double> negativeWords) {
        double score = 0;
        double overallScore = 0;
        
        String[] words = review.toLowerCase().split("\\W+");

        for (String word : words) {
            word = word.trim(); 

            if (positiveWords.containsKey(word)) {
                score += positiveWords.get(word);  
                System.out.println("Positive match: " + word + " (Score: " + positiveWords.get(word) + ")"); 
                overallScore+=score; 
            } else if (negativeWords.containsKey(word)) {
                score += negativeWords.get(word);  
                System.out.println("Negative match: " + word + " (Score: " + negativeWords.get(word) + ")");
                overallScore+=score; 
            }
        }
        return score;
    }

   
    public static void analyzeReview(String review, Map<String, Double> positiveWords, Map<String, Double> negativeWords) {
        double score = calculateSentimentScore(review, positiveWords, negativeWords);

        if (score > 1) {
            System.out.println("Positive review: " + review);
        }else if(score > -0.5 || score < 0.5){
            System.out.println("Mixed review: " + review);
        }else {
            System.out.println("Negative review: " + review);
        }
    }

    public static void main(String[] args) {
      
        String positiveWordsFile = "positiveAdjectives.txt";
        String negativeWordsFile = "negativeAdjectives.txt";

        try {
         
            Map<String, Double> positiveWords = loadWordsFromFile(positiveWordsFile);
            Map<String, Double> negativeWords = loadWordsFromFile(negativeWordsFile);

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
