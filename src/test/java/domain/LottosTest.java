package domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottosTest {

	private final CustomLottoGenerator customLottoGenerator = new CustomLottoGenerator();

	@DisplayName("로또목록 생성 테스트")
	@Test
	void initTest() {
		assertDoesNotThrow(() -> new Lottos(14, new RandomLottoGenerator()));
	}

	@DisplayName("")
	@Test
	void getRanksTest() {
		List<List<Integer>> numbers = new ArrayList<>();
		numbers.add(Arrays.asList(1,2,3,8,9,10));
		numbers.add(Arrays.asList(1,2,3,8,9,45));
		numbers.add(Arrays.asList(11,12,13,14,15,16));
		customLottoGenerator.initNumbers(numbers);

		Lottos lottos = new Lottos(3, customLottoGenerator);

		List<Integer> answerNumbers = Arrays.asList(1,2,3,8,9,10);
		Balls answerBalls = new Balls(answerNumbers);
		Ball bonusBall = new Ball(45);

		List<Rank> actual = lottos.getRanks(answerBalls, bonusBall);
		List<Rank> expected = Arrays.asList(Rank.FIRST_GRADE, Rank.SECOND_GRADE, Rank.FAIL_GRADE);

		assertThat(actual).isEqualTo(expected);
	}
}
