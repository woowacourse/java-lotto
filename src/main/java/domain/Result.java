package domain;

import java.util.*;

import static java.util.stream.Collectors.toMap;

public class Result {
	private final Map<ResultStatics, Integer> results
		= Arrays.stream(ResultStatics.values())
		.collect(toMap(resultStatics -> resultStatics, count -> 0));
	private final float profitRate;

	public Result(List<ResultStatics> totalResult, int money) {
		for (ResultStatics resultStatics : totalResult) {
			addResult(resultStatics);
		}
		profitRate = calculateProfitRate(money);
	}

	private void addResult(ResultStatics resultStatics) {
		results.put(resultStatics, results.get(resultStatics) + 1);
	}

	private float calculateProfitRate(int money) {
		int profit = 0;
		for (ResultStatics resultStatics : results.keySet()) {
			profit += (resultStatics.getPrice() * results.get(resultStatics));
		}
		return (float) profit / money;
	}

	public Map<ResultStatics, Integer> getResults() {
		return Collections.unmodifiableMap(results);
	}

	public float getProfitRate() {
		return profitRate;
	}
}
