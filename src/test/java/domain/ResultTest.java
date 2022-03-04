package domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {
	private static Map<Result, Float> testCase = new HashMap<>();

	@BeforeAll
	static void generateTestCase() {
		for (ResultStatics resultStatics : ResultStatics.values()) {
			Result result = new Result();
			result.addResult(resultStatics);
			testCase.put(result, (float) resultStatics.getPrice() / 1000);
		}
	}

	@Test
	@DisplayName("올바른 수익률을 산출하였는지 확인")
	void correctProfitRate() {
		for (Result result : testCase.keySet()) {
			result.calculateProfitRate(1000);
			assertThat(result.getProfitRate()).isEqualTo(testCase.get(result));
		}
	}
}
