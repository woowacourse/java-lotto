import static org.assertj.core.api.Assertions.assertThat;

import domain.AnswerLotto;
import domain.Lotto;
import domain.Lottos;

import java.util.*;

import domain.ResultStatics;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LottosTest {
	static Lottos lottos;
	static AnswerLotto answerLotto;

	@BeforeAll
	static void setUp() {
		List<Lotto> nowLotto = new ArrayList<>();
		nowLotto.add(new Lotto(() -> new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6))));
		nowLotto.add(new Lotto(() -> new ArrayList<Integer>(Arrays.asList(7, 8, 9, 10, 11, 12))));
		nowLotto.add(new Lotto(() -> new ArrayList<Integer>(Arrays.asList(13, 14, 15, 16, 17, 18))));
		nowLotto.add(new Lotto(() -> new ArrayList<Integer>(Arrays.asList(19, 20, 21, 22, 23, 24))));
		nowLotto.add(new Lotto(() -> new ArrayList<Integer>(Arrays.asList(25, 26, 27, 28, 29, 30))));
		lottos = new Lottos(nowLotto);
		answerLotto = new AnswerLotto(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 43, 44, 45)), 31);
	}

	@Test
	void generateCorrectCountOfLotto() {
		assertThat(lottos.getLottosSize()).isEqualTo(3);
	}

	@Test
	void generateEachResultStatics() {
		Map<ResultStatics, Integer> expected = new HashMap<>();

		for (ResultStatics resultStatic : ResultStatics.values()) {
			expected.put(resultStatic, 0);
		}

		expected.put(ResultStatics.NOTHING, 4);
		expected.put(ResultStatics.THREE, 1);

		assertThat(lottos.generateEachCount(answerLotto)).isEqualTo(expected);
	}

	@Test
	void generateProfitRate() {
		assertThat(lottos.generateProfitRatio(answerLotto)).isEqualTo(1.0f);
	}
}
