package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {
	private Map<ResultStatics, Integer> results;

	public Result() {
		results = new LinkedHashMap<>();
		for (ResultStatics resultStatics : ResultStatics.values()) {
			results.put(resultStatics, 0);
		}
	}

	public void addResult(ResultStatics resultStatics) {
		results.put(resultStatics, results.get(resultStatics) + 1);
	}

	public float calculateProfitRate(int money) {
		int profit = 0;
		for (ResultStatics resultStatics : results.keySet()) {
			profit += (resultStatics.getPrice() * results.get(resultStatics));
		}
		return (float) profit / money;
	}

	public Map<ResultStatics, Integer> getResults() {
		return results;
	}

}
