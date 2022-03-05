package strategy;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputLottoNumbersGenerationStrategyTest {
	@Test
	@DisplayName("로또 번호 숫자 사이즈가 6개가 아닌 경우")
	void validateInputLottoWinningNumberSize() {
		class Strategy extends InputLottoNumbersGenerationStrategy {
			@Override
			public String inputNumbers() {
				return "1,2,3,4,5";
			}
		}
		Strategy strategy = new Strategy();
		assertThatThrownBy(
			() -> strategy.generate(6))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[Error]: 로또는 6개의 숫자여야 합니다.");
	}

	@Test
	@DisplayName("로또 번호에 중복이 있는지 검증")
	void validateWinningNumberReduplication() {
		class Strategy extends InputLottoNumbersGenerationStrategy {
			@Override
			public String inputNumbers() {
				return "1,2,3,4,5,5";
			}
		}
		Strategy strategy = new Strategy();
		assertThatThrownBy(
			() -> strategy.generate(6))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("[Error]: 로또 번호는 중복이 있으면 안됩니다");
	}
}
