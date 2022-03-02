package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import lotto.model.number.LottoBall;

public class AutoLottoTest {

	@DisplayName("같은 숫자 리스트로 생성된 로또여도, 다른 값을 갖는다")
	@Test
	void generate_two_lottos() {
		List<LottoBall> lottoBalls1 = Arrays.asList(
				LottoBall.from("1"),
				LottoBall.from("2"),
				LottoBall.from("3"),
				LottoBall.from("4"),
				LottoBall.from("5"),
				LottoBall.from("6")
		);
		AutoLotto autoLotto1 = new AutoLotto(lottoBalls1);

		List<LottoBall> lottoBalls2 = Arrays.asList(
				LottoBall.from("6"),
				LottoBall.from("4"),
				LottoBall.from("1"),
				LottoBall.from("5"),
				LottoBall.from("2"),
				LottoBall.from("3")
		);
		AutoLotto autoLotto2 = new AutoLotto(lottoBalls2);

		assertThat(autoLotto1).isNotEqualTo(autoLotto2);
	}

	@DisplayName("6개의 숫자를 생성한다")
	@Test
	void generate_six_number() {
		AutoLotto autoLotto = new AutoLotto(AutoLotto.selectNumbers());

		assertThat(autoLotto.getAutoLotto().size()).isEqualTo(6);
	}

	@DisplayName("6개의 숫자가 오름차순으로 정렬된다")
	@Test
	void sort_ascending() {
		AutoLotto autoLotto = new AutoLotto(AutoLotto.selectNumbers());
		List<LottoBall> numbers = autoLotto.getAutoLotto();

		for (int index = 0; index < (numbers.size() - 1); index++) {
			assertThat(numbers.get(index).getNumber() < numbers.get(index + 1).getNumber()).isTrue();
		}
	}
}
