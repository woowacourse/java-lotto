package lotto.util;

import lotto.domain.LottoNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/22
 */
public class StringUtilsTest {

	@Test
	void splitIntoLottoNumbers_올바른_동작_확인() {
		String input = "1,2 ,3, 4, 5 , 6";
		assertThat(StringUtils.splitIntoLottoNumbers(input))
				.isEqualTo(
						Arrays.asList(
								LottoNumber.of(1),
								LottoNumber.of(2),
								LottoNumber.of(3),
								LottoNumber.of(4),
								LottoNumber.of(5),
								LottoNumber.of(6)
						)
				);
	}

	@ParameterizedTest
	@NullSource
	void splitIntoLottoNumbers_null_입력_예외처리(String input) {
		assertThatNullPointerException()
				.isThrownBy(() -> StringUtils.splitIntoLottoNumbers(input))
				.withMessage("로또번호로 null 이 입력될 수 없습니다.");
	}

	@ParameterizedTest
	@EmptySource
	void splitIntoLottoNumbers_빈_열문자_입력_예외처리(String input) {
		assertThatIllegalArgumentException()
				.isThrownBy(() -> StringUtils.splitIntoLottoNumbers(input))
				.withMessage("아무것도 입력하지 않으셨습니다.");
	}

	@Test
	void splitIntoLottoNumbers_잘못된_형식_입력_예외처리() {
		String input = "1,2 3,3, 4, 5,  , 6";
		assertThatIllegalArgumentException()
				.isThrownBy(() -> StringUtils.splitIntoLottoNumbers(input))
				.withMessage("잘못된 형식으로 입력하셨습니다.");
	}
}
