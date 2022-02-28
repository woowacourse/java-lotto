package domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static domain.CommonLogic.generateNumberList;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoTicketsTest {
	static LottoTickets lottoTickets;
	static AnswerLotto answerLotto;
	static Result result;

	@BeforeAll
	static void setUp() {
		List<LottoNumbers> nowLotto = new ArrayList<>();
		nowLotto.add(LottoNumbers.of(generateNumberList(1, 2, 3, 4, 5, 6)));
		nowLotto.add(LottoNumbers.of(generateNumberList(7, 8, 9, 10, 11, 12)));
		nowLotto.add(LottoNumbers.of(generateNumberList(13, 14, 15, 16, 17, 18)));
		nowLotto.add(LottoNumbers.of(generateNumberList(19, 20, 21, 22, 23, 24)));
		nowLotto.add(LottoNumbers.of(generateNumberList(25, 26, 27, 28, 29, 30)));
		lottoTickets = LottoTickets.of(new Money(5000), (int count) -> nowLotto);
		answerLotto = AnswerLotto.of(generateNumberList(1, 2, 3, 43, 44, 45), 31);

		result = lottoTickets.generateResult(answerLotto);
	}

	@Test
	@DisplayName("입력된 구매금액만큼 로또를 구매하였는지 확인")
	void CorrectCountOfLotto() {
		assertThat(lottoTickets.getLottoTicketsSize()).isEqualTo(5);
	}

	@Test
	@DisplayName("올바른 당첨 결과를 산출하였는지 확인")
	void correctEachResultStatics() {
		Map<ResultStatics, Integer> expected = new HashMap<>();

		for (ResultStatics resultStatic : ResultStatics.values()) {
			expected.put(resultStatic, 0);
		}

		expected.put(ResultStatics.NOTHING, 4);
		expected.put(ResultStatics.THREE, 1);

		assertThat(result.getResults()).isEqualTo(expected);
	}

	@Test
	@DisplayName("올바른 수익률을 산출하였는지 확인")
	void correctProfitRate() {
		assertThat(result.getProfitRate()).isEqualTo(1.0f);
	}

}
