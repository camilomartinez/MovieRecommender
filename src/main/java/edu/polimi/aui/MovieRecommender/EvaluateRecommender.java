package edu.polimi.aui.MovieRecommender;

import java.io.File;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

public class EvaluateRecommender {

	public static void main(String[] args) throws Exception {
		// Reproducible results
		RandomUtils.useTestSeed();
		DataModel model = new FileDataModel(new File("data/dataset.csv"));
		RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
		RecommenderBuilder builder = new MyRecommender();
		//Third argument: Percentage of sample data used for testing
		//Fourth argument: Percentage of data taken to perform the evaluation
		//				   useful for a big data set.
		double result = evaluator.evaluate(builder, null, model, 0.9, 1.0);
		System.out.println(result);
	}
}

class MyRecommender implements RecommenderBuilder {

	public Recommender buildRecommender(DataModel dataModel) throws TasteException {
		UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, dataModel);
		return new GenericUserBasedRecommender(dataModel, neighborhood, similarity);	
	}
	
}
