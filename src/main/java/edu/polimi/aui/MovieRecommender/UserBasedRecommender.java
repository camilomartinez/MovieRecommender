package edu.polimi.aui.MovieRecommender;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class UserBasedRecommender implements RecommenderBuilder {

	public Recommender buildRecommender(DataModel dataModel) throws TasteException {
		UserSimilarity similarity = new LogLikelihoodSimilarity(dataModel);
		UserNeighborhood neighborhood = new NearestNUserNeighborhood(10, similarity, dataModel);
		return new GenericUserBasedRecommender(dataModel, neighborhood, similarity);	
	}
}
