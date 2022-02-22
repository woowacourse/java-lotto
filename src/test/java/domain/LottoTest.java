package domain;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

	private final CustomLottoGenerator customLottoGenerator = new CustomLottoGenerator();

	@DisplayName("로또 생성 테스트")
	@Test
	void initTest() {
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
		customLottoGenerator.initNumbers(numbers);

		assertDoesNotThrow(() -> new Lotto(customLottoGenerator));
	}

	@DisplayName("당첨번호와 일치 갯수 확인 테스트")
	@Test
	void countTest() {
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
		customLottoGenerator.initNumbers(numbers);

		 Lotto lotto = new Lotto(customLottoGenerator);

		 Balls answer = new Balls(numbers);

		 assertThat(lotto.countMatches(answer, new Ball(7))).isEqualTo(Rank.FIRST_GRADE);
	}
}
