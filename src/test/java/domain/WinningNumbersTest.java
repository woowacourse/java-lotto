package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

	@Test
	@DisplayName("당첨 번호와 보너스 넘버가 중복되면 예외를 발생")
	void checkDuplicateBonusNumber() {
		Lotto lotto = new Lotto(Stream.of(1, 5, 9, 11, 16, 35)
			.map(Number::new)
			.collect(Collectors.toList()));

		Number bonus = new Number(11);
		assertThatThrownBy(() -> new WinningNumbers(lotto,bonus))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("당첨 번호와 보너스 번호는 중복될 수 없습니다");
	}
}