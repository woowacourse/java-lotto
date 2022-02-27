package lotto.model.number;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BonusNumberTest {
	private WinningNumbers winningNumbers;

	@BeforeEach
	public void initializeStandardNumbers() {
		List<String> winningNumbers = Arrays.asList("1", "2", "3", "4", "5", "6");
		this.winningNumbers = WinningNumbers.from(winningNumbers);
	}

	@DisplayName("보너스 번호가 당첨 번호들과 중복되면 예외가 발생한다")
	@Test
	void duplicate_exception() {
		assertThatThrownBy(() -> {
			BonusNumber.from(Number.from("1"), winningNumbers);
		}).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다");
	}
}
