package lotto.model.lotto;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTest {

	@DisplayName("Lotto에 LottoBall이 6개가 아니면 예외를 반환한다")
	@Test
	void count_exception() {
		List<String> manualLottoNumber = Arrays.asList("1", "2", "3", "4");

		assertThatThrownBy(() -> {
			Lotto.fromManual(manualLottoNumber);
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 로또 번호는 6개로 입력해야 합니다");
	}

	@DisplayName("Lotto 의 LottoBall 에 중복된 값이 있으면 예외를 반환한다")
	@Test
	void duplicate_exception() {
		List<String> manualLottoNumber = Arrays.asList("1", "1", "2", "3", "4", "5");

		assertThatThrownBy(() -> {
			Lotto.fromManual(manualLottoNumber);
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 한 개의 로또 내에서 숫자가 중복될 수 없습니다");
	}

	@DisplayName("Lotto 의 LottoBall 에 1 미만 값이 있으면 예외를 반환한다")
	@Test
	void bound_exception_under() {
		List<String> manualLottoNumber = Arrays.asList("-100", "1", "2", "3", "4", "5");

		assertThatThrownBy(() -> {
			Lotto.fromManual(manualLottoNumber);
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 로또 번호는 1 이상 45 이하로 입력해주세요");
	}

	@DisplayName("Lotto 의 LottoBall 에 45 초과 값이 있으면 예외를 반환한다")
	@Test
	void bound_exception_over() {
		List<String> manualLottoNumber = Arrays.asList("100", "1", "2", "3", "4", "5");

		assertThatThrownBy(() -> {
			Lotto.fromManual(manualLottoNumber);
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 로또 번호는 1 이상 45 이하로 입력해주세요");
	}

	@DisplayName("같은 숫자 리스트로 생성된 로또여도, 다른 값을 갖는다")
	@Test
	void generate_two_lottos() {
		List<String> lottoBalls1 = Arrays.asList("1", "2", "3", "4", "5", "6");
		Lotto lotto1 = Lotto.fromManual(lottoBalls1);

		List<String> lottoBalls2 = Arrays.asList("6", "1", "5", "2", "4", "3");
		Lotto lotto2 = Lotto.fromManual(lottoBalls2);

		assertThat(lotto1).isNotEqualTo(lotto2);
	}

	@DisplayName("6개의 숫자를 생성한다")
	@Test
	void generate_six_number() {
		Lotto lotto = Lotto.fromAuto();

		assertThat(lotto.getLotto().size()).isEqualTo(6);
	}

	@DisplayName("6개의 숫자가 오름차순으로 정렬된다")
	@Test
	void sort_ascending() {
		Lotto lotto = Lotto.fromAuto();
		List<LottoBall> numbers = lotto.getLotto();

		for (int index = 0; index < (numbers.size() - 1); index++) {
			assertThat(numbers.get(index).getNumber() < numbers.get(index + 1).getNumber()).isTrue();
		}
	}
}
