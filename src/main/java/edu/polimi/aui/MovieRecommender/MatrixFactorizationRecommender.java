package edu.polimi.aui.MovieRecommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.recommender.svd.Factorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDPlusPlusFactorizer;
import org.apache.mahout.cf.taste.impl.recommender.svd.SVDRecommender;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.Recommender;

public class MatrixFactorizationRecommender implements RecommenderBuilder {

	public Recommender buildRecommender(DataModel dataModel) throws TasteException {
		// As suggested in Mahout in Action
		//Factorizer factorizer = new ALSWRFactorizer(dataModel, 10, 0.05, 10);
		// SVD ++
		Factorizer factorizer = new SVDPlusPlusFactorizer(dataModel, 10, 10);
		return new SVDRecommender(dataModel, factorizer);	
	}
}
