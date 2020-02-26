package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import lotto.domain.exception.InvalidLottoException;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;

class LottoTest {
	@DisplayName("Lotto 생성자에 LottoNumber List 입력이 들어올 때 객체 생성")
	@Test
	void constructor_NumberList_CreateLotto() {
		Set<LottoNumber> lottoNumbers = new HashSet<>();
		lottoNumbers.add(LottoNumber.valueOf(1));
		lottoNumbers.add(LottoNumber.valueOf(2));
		lottoNumbers.add(LottoNumber.valueOf(3));
		lottoNumbers.add(LottoNumber.valueOf(4));
		lottoNumbers.add(LottoNumber.valueOf(5));
		lottoNumbers.add(LottoNumber.valueOf(6));

		assertThat(new Lotto(lottoNumbers)).isInstanceOf(Lotto.class);
	}

	@DisplayName("Lotto 생성자에 null 입력이 들어올 때 InvalidLottoException 발생")
	@ParameterizedTest
	@NullSource
	void validateNull_NullNumberList_ExceptionThrown(Set<LottoNumber> lottoNumbers) {
		assertThatThrownBy(() -> new Lotto(lottoNumbers))
			.isInstanceOf(InvalidLottoException.class)
			.hasMessage(InvalidLottoException.NULL);
	}

	@DisplayName("Lotto 생성자에 사이즈가 올바르지 않은 Set 입력이 들어올 때 InvalidLottoException 발생")
	@Test
	void validateSize_NullNumberList_ExceptionThrown() {
		Set<LottoNumber> lottoNumbers = new HashSet<>(5);
		assertThatThrownBy(() -> new Lotto(lottoNumbers))
			.isInstanceOf(InvalidLottoException.class)
			.hasMessage(InvalidLottoException.WRONG_SIZE);
	}
}
