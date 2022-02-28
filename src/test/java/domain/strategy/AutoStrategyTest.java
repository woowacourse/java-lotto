package domain.strategy;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AutoStrategyTest {

	@DisplayName("렌덤 로또 번호 생성 테스트")
	@Test
	void generate() {
		List<Integer> numbers = (new AutoStrategy()).generate();

		assertThat(numbers.size()).isEqualTo(6);
	}
}
