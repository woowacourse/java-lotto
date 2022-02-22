import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

/**
 *     - [ 예외 ] 각 숫자는 1 ~ 45 범위 내에 있어야한다.
 *     - [ 예외 ] 빈 문자열이 아니어야 한다.
 */
public class LottoNumberTest {
	//- [ 예외 ] 문자가 아니어야 한다.
	@Test
	void 문자_실패() {
		//given, when
		assertThatThrownBy(() -> new LottoNumber("one"))
			.isInstanceOf(NumberFormatException.class);
		//then
	}

	@Test
	void 문자_성공() {
		//given, when
		assertThatCode(() -> new LottoNumber("45"))
			.doesNotThrowAnyException();
		//then
	}

	// - [ 예외 ] 빈 문자열이 아니어야 한다.
	@Test
	void 빈문자열() {
		assertThatThrownBy(() -> new LottoNumber(""))
			.isInstanceOf(NumberFormatException.class);
	}

	//[ 예외 ] 각 숫자는 1 ~ 45 범위 내에 있어야한다.
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
