package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AutoLottoTest {

	@DisplayName("같은 숫자 리스트로 생성된 로또여도, 다른 값을 갖는다")
	@Test
	void generate_two_lottos() {
		AutoLotto autoLotto1 = new AutoLotto(Arrays.asList(1, 2, 3, 4, 5, 6));
		AutoLotto autoLotto2 = new AutoLotto(Arrays.asList(6, 4, 1, 5, 2, 3));

		assertThat(autoLotto1).isNotEqualTo(autoLotto2);
	}

	@DisplayName("6개의 숫자를 생성한다")
	@Test
	void generate_six_number() {
		AutoLotto autoLotto = new AutoLotto(AutoLotto.selectNumbers());

		assertThat(autoLotto.getNumbers().size()).isEqualTo(6);
	}

	@DisplayName("6개의 숫자가 오름차순으로 정렬된다")
	@Test
	void sort_ascending() {
		AutoLotto autoLotto = new AutoLotto(AutoLotto.selectNumbers());
		List<Integer> numbers = autoLotto.getNumbers();

		for (int index = 0; index < (numbers.size() - 1); index++) {
			assertThat(numbers.get(index) < numbers.get(index + 1)).isTrue();
		}
	}
}
