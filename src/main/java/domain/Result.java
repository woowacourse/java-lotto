package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {
	private Map<ResultStatics, Integer> results;
	private float profitRate;

	public Result() {
		results = new LinkedHashMap<>();
		for (ResultStatics resultStatics : ResultStatics.values()) {
			results.put(resultStatics, 0);
		}
		profitRate = 0;
	}

	public void addResult(ResultStatics resultStatics) {
		results.put(resultStatics, results.get(resultStatics) + 1);
	}

	public void calculateProfitRate(int money) {
		for (ResultStatics resultStatics : results.keySet()) {
			profitRate += (resultStatics.getPrice() * results.get(resultStatics));
		}
		profitRate = profitRate / money;
	}

	public Map<ResultStatics, Integer> getResults() {
		return results;
	}

	public float getProfitRate() {
		return profitRate;
	}
}
