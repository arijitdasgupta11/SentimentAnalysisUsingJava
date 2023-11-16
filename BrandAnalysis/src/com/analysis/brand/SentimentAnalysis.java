package com.analysis.brand;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

public class SentimentAnalysis {

	public synchronized static void analyze(List<String> comments, List<Double> ratings, String brandname) {

		double avgrating = ratings.stream().collect(Collectors.averagingDouble(x -> x));

		double sentimentScore = analyzeSentiment(comments);
		double combinedScore = combineSentimentAndRating(sentimentScore, avgrating, brandname);

		System.out.println("Sentiment Score: " + sentimentScore);
		System.out.println("Rating: " + avgrating);
		System.out.println("Combined Score: " + combinedScore);
	}

	private static double analyzeSentiment(List<String> comments) {
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");

		try {
			StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
			double mainSentiment = 0;
			for (String text : comments) {
				Annotation annotation = pipeline.process(text);

				for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
					int sentiment = RNNCoreAnnotations
							.getPredictedClass(sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class));
					mainSentiment += sentiment;
				}
				mainSentiment = mainSentiment / annotation.get(CoreAnnotations.SentencesAnnotation.class).size();
			}

			return mainSentiment = mainSentiment / comments.size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0.0;

	}

	private synchronized static double combineSentimentAndRating(double sentimentScore, double rating,
			String brandname) {
		int brandid = DatabaseOperations.brandExists(brandname);

		double baseScore = DatabaseOperations.getCurrentScore(brandid);
		double avg = (sentimentScore + rating) / 2.0;

		if (avg > 2.5) {
			baseScore += 0.5;
		} else if (avg >= 2.0 && avg <= 2.5) {
			baseScore += 0.1;
		} else if (avg >= 1.5 && avg < 2) {
			baseScore -= 0.1;
		} else if (avg < 1.5) {
			baseScore -= 1;
		}

		DatabaseOperations.updateCurrentScore(baseScore, brandid);
		
		return baseScore;
	}
}
