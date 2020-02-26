package lotto.domain.lottoTicket;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import lotto.domain.lottoNumber.LottoNumber;
import lotto.domain.lottoNumber.LottoNumberCache;
import lotto.domain.result.MatchCount;

class LottoTicketTest {
	@Test
	void LottoTicket_ValidLottoNumbers_GenerateInstance() {
		List<LottoNumber> value = LottoNumberCache.asLottoNumber(1, 2, 3, 4, 5, 6);

		assertThat(new LottoTicket(value)).isInstanceOf(LottoTicket.class);
	}

	@ParameterizedTest
	@NullAndEmptySource
	void validateNullOrEmpty_NullOrEmptyInput_InvalidLottoTicketExceptionThrown(List<LottoNumber> value) {
		assertThatThrownBy(() -> new LottoTicket(value))
			.isInstanceOf(InvalidLottoTicketException.class)
			.hasMessage(InvalidLottoTicketException.NULL_OR_EMPTY);
	}

	@Test
	void validateSize_InvalidSizeOfLottoNumbers_InvalidLottoTicketExceptionThrown() {
		List<LottoNumber> value = LottoNumberCache.asLottoNumber(1, 2, 3, 4, 5);

		assertThatThrownBy(() -> new LottoTicket(value))
			.isInstanceOf(InvalidLottoTicketException.class)
			.hasMessage(InvalidLottoTicketException.WRONG_SIZE);
	}

	@Test
	void validateDuplication_DuplicateExistLottoNumbers_InvalidLottoTicketExceptionThrown() {
		List<LottoNumber> value = LottoNumberCache.asLottoNumber(1, 2, 3, 4, 5, 5);

		assertThatThrownBy(() -> new LottoTicket(value))
			.isInstanceOf(InvalidLottoTicketException.class)
			.hasMessage(InvalidLottoTicketException.DUPLICATION);
	}

	@Test
	void valueOf_InputLottoNumbersByString_GenerateInstance() {
		String value = "1, 2, 3, 4, 5, 6";

		assertThat(LottoTicket.valueOf(value)).isInstanceOf(LottoTicket.class);
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6})
	void contains_ContainedLottoNumber_ReturnTrue(int value) {
		LottoNumber lottoNumber = LottoNumber.valueOf(value);
		List<LottoNumber> lottoNumbers = LottoNumberCache.asLottoNumber(1, 2, 3, 4, 5, 6);

		assertThat(lottoNumbers.contains(lottoNumber)).isTrue();
	}

	@ParameterizedTest
	@ValueSource(ints = {7, 8, 9})
	void contains_NotContainedLottoNumber_ReturnFalse(int value) {
		LottoNumber lottoNumber = LottoNumber.valueOf(value);
		List<LottoNumber> lottoNumbers = LottoNumberCache.asLottoNumber(1, 2, 3, 4, 5, 6);

		assertThat(lottoNumbers.contains(lottoNumber)).isFalse();
	}

	@Test
	void countMatchingWith_WinningLottoTicket_ReturnMatchCount() {
		LottoTicket lottoTicket = LottoTicket.valueOf("1, 2, 3, 4, 5, 6");
		LottoTicket winningLottoTicket = LottoTicket.valueOf("2, 3, 4, 5, 7, 9");

		MatchCount actual = lottoTicket.countMatchingWith(winningLottoTicket);

		MatchCount expected = new MatchCount(4);
		assertThat(actual).isEqualTo(expected);
	}
}
