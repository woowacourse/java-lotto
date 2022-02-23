package domain;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class LottoNumberTest {

	@Test
	void 문자_실패() {
		assertThatThrownBy(() -> new LottoNumber("one"))
			.isInstanceOf(NumberFormatException.class);
	}

	@Test
	void 문자_성공() {
		assertThatCode(() -> new LottoNumber("45"))
			.doesNotThrowAnyException();
	}

	@Test
	void 빈문자열() {
		assertThatThrownBy(() -> new LottoNumber(""))
			.isInstanceOf(NumberFormatException.class);
	}

	@Test
	void 범위_불만족_최대() {
		assertThatThrownBy(() -> new LottoNumber("46"))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void 범위_불만족_최소() {
		assertThatThrownBy(() -> new LottoNumber("0"))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
