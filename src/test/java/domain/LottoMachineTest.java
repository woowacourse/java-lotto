package domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMachineTest {

	static Result result;

	@BeforeAll
	static void setResult() {
		LottoMachine lottoMachine = new LottoMachine(1000);
		AnswerLotto answerLotto = AnswerLotto.of(List.of(1, 2, 3, 4, 5, 6), 7);
		lottoMachine.purchase(
			List.of(
				List.of(1, 2, 3, 4, 5, 6), // FIRST
				List.of(1, 2, 3, 4, 5, 7), // SECOND
				List.of(1, 2, 3, 4, 5, 8), // THIRD
				List.of(1, 2, 3, 4, 8, 9), // FOURTH
				List.of(1, 2, 3, 8, 9, 10), // FIFTH
				List.of(8, 9, 10, 11, 12, 13) // NOTHING
			)
		);

		result = lottoMachine.generateResult(answerLotto);
	}

	@Test
	@DisplayName("올바른 상세결과를 산출하는지 확인")
	void generateRightResultStatics() {
		Map<ResultStatics, Integer> detailResultsStatics = result.getResults();

		for (ResultStatics resultStatics : detailResultsStatics.keySet()) {
			assertThat(detailResultsStatics.get(resultStatics)).isEqualTo(1);
		}
	}

}
