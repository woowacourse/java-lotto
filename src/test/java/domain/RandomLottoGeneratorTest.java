package domain;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomLottoGeneratorTest {

	@DisplayName("렌덤 로또 번호 생성 테스트")
	@Test
	void generate() {
		List<Integer> numbers =  (new RandomLottoGenerator()).generate();

		assertThat(numbers.size()).isEqualTo(6);
	}

}
