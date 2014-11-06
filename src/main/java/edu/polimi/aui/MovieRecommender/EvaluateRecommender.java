package edu.polimi.aui.MovieRecommender;

import java.io.File;

import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.common.RandomUtils;

public class EvaluateRecommender {

	public static void main(String[] args) throws Exception {
		// Reproducible results
		RandomUtils.useTestSeed();
		DataModel model = new FileDataModel(new File("data/train.csv"));
		RecommenderEvaluator evaluator = new RMSRecommenderEvaluator();
		RecommenderBuilder builder = new UserBasedRecommender();
		// Percentage of sample data used for testing
		double percentageForTraining = 0.9;
		// Percentage of data taken to perform the evaluation
		// useful for a big data set.
		double percentageOfDataToEvaluate = 0.001; //0.1% 811 out of 811k 		
		double result = evaluator.evaluate(builder, null, model, percentageForTraining, percentageOfDataToEvaluate);
		System.out.print("RMSE for UserBased:");
		System.out.println(result);
		builder = new MatrixFactorizationRecommender();
		result = evaluator.evaluate(builder, null, model, percentageForTraining, percentageOfDataToEvaluate);
		System.out.print("RMSE for MatrixFactorization:");
		System.out.println(result);
	}
}
