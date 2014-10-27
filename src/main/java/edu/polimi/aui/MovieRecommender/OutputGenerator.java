package edu.polimi.aui.MovieRecommender;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;

public class OutputGenerator {

	private static String trainFile = "data/shortTrain.csv";
	private static String testFile = "data/shortTest.csv";
	private static String outputFile = "data/submission.csv";
	private static int numberOfRecommendations = 5;
	
	public static void main(String[] args) throws IOException, TasteException {
    	DataModel model = createModel(trainFile);
    	RecommenderBuilder builder = new UserBasedRecommender();
    	Recommender recommender = builder.buildRecommender(model);
    	
    	BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
    	bw.write("UserId,RecommendedMovieIds");
    	bw.newLine();
    	
    	BufferedReader br = new BufferedReader(new FileReader(testFile));
    	//Skip header
    	br.readLine();
    	
		String line;
		while((line = br.readLine()) != null) {
			long userId = Long.decode(line);
			List<RecommendedItem> recommendations = recommender.recommend(userId, numberOfRecommendations);
			// User ID
			bw.write(userId + ",");
			//System.out.println("Recommending for: " + userId);
			System.out.println(userId);
			if (recommendations.size() < 5)
			{
				System.out.println("Unable to generate five recommendations for user: " +userId);
			}
			// Recommended items
	    	for (RecommendedItem recommendation : recommendations) {
	    		bw.write(recommendation.getItemID() + " ");
	    	}
	    	bw.newLine();
		}
		
		br.close();
    	bw.close();
	}
	
	private static DataModel  createModel(String sourceFile) throws IOException {
		return new FileDataModel(new File(sourceFile));
	}
}
