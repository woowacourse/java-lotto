package lotto.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringSplitUtilTest {
	@DisplayName("하나의 문자열을 구분자를 기준으로 분리하는 기능 확인")
	@Test
	void splitNameTest() {
		String[] numbers = StringSplitUtil.splitRawLottoNumbers("1,2,3,4,5,6");
		assertThat(numbers).containsExactly("1", "2", "3", "4", "5", "6");
	}
}