package domain;

import java.util.HashMap;
import java.util.Map;

public class Result {
	private Map<ResultStatics, Integer> results;
	private float profitRate = 0;

	public Result() {
		results = new HashMap<>();
		for (ResultStatics resultStatics : ResultStatics.values()) {
			results.put(resultStatics, 0);
		}
	}

	public void addResult(ResultStatics resultStatics) {
		results.put(resultStatics, results.get(resultStatics) + 1);
	}

	public void setProfitRate(int money) {
		int profit = 0;
		for (ResultStatics resultStatics : results.keySet()) {
			profit += (resultStatics.getPrice() * results.get(resultStatics));
		}
		this.profitRate = (float) profit / money;
	}

	public Map<ResultStatics, Integer> getResults() {
		return results;
	}

	public float getProfitRate() {
		return profitRate;
	}
}
