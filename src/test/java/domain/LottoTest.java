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
}
