package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LottoTest {

	@Test
	void 개수_성공() {
		assertThatCode(() -> new Lotto(Arrays.asList(new LottoNumber("1"),
			new LottoNumber("2"),
			new LottoNumber("3"),
			new LottoNumber("4"),
			new LottoNumber("5"),
			new LottoNumber("6")))).doesNotThrowAnyException();
	}

	@Test
	void 개수_실패() {
		assertThatThrownBy(() -> new Lotto(Arrays.asList(new LottoNumber("1"),
			new LottoNumber("2"),
			new LottoNumber("3"),
			new LottoNumber("4"),
			new LottoNumber("5")))).isInstanceOf(IllegalArgumentException.class);
	}

	//    - [ 예외 ] 숫자 중복 불가
	@Test
	void 중복_불가_성공() {
		assertThatThrownBy(() -> new Lotto(Arrays.asList(new LottoNumber("1"),
			new LottoNumber("2"),
			new LottoNumber("3"),
			new LottoNumber("4"),
			new LottoNumber("5"),
			new LottoNumber("5")))).isInstanceOf(IllegalArgumentException.class);
	}
}
