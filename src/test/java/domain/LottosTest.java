package domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import util.RandomLottoNumberGenerator;

import java.util.*;

import static domain.CommonLogic.generateNumberList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottosTest {
	static Lottos lottos;
	static AnswerLotto answerLotto;
	static ResultDTO resultDTO;

	@BeforeAll
	static void setUp() {
		List<Lotto> nowLotto = new ArrayList<>();
		nowLotto.add(new Lotto(() -> generateNumberList(1, 2, 3, 4, 5, 6)));
		nowLotto.add(new Lotto(() -> generateNumberList(7, 8, 9, 10, 11, 12)));
		nowLotto.add(new Lotto(() -> generateNumberList(13, 14, 15, 16, 17, 18)));
		nowLotto.add(new Lotto(() -> generateNumberList(19, 20, 21, 22, 23, 24)));
		nowLotto.add(new Lotto(() -> generateNumberList(25, 26, 27, 28, 29, 30)));
		lottos = new Lottos(nowLotto);
		answerLotto = new AnswerLotto(
			new AnswerLottoNumbers(generateNumberList(1, 2, 3, 43, 44, 45)), new BonusNumber(31));

		resultDTO = lottos.generateResult(answerLotto);
	}

	@Test
	@DisplayName("입력된 구매금액만큼 로또를 구매하였는지 확인")
	void CorrectCountOfLotto() {
		assertThat(lottos.getLottosSize()).isEqualTo(5);
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 1234})
	@DisplayName("구입금액 : 로또 금액으로 나누어 떨어지지 않는 금액을 입력 한 경우 예외 발생")
	void validatePrice(int price) {
		assertThatThrownBy(() -> Lottos.of(price, new RandomLottoNumberGenerator())).isInstanceOf(
			IllegalArgumentException.class);
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

		assertThat(resultDTO.getResults()).isEqualTo(expected);
	}

	@Test
	@DisplayName("올바른 수익률을 산출하였는지 확인")
	void correctProfitRate() {
		assertThat(resultDTO.getProfitRate()).isEqualTo(1.0f);
	}

}
