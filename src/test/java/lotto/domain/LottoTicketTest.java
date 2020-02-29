package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoTicketTest {
	@DisplayName("정적 팩토리 메소드 테스트 - 6자리를 넘는 경우")
	@Test
	void checkValidationWhenOverNumbersSize() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			LottoTicket.of("1, 2, 3, 4, 5, 6, 7")
		).withMessage("로또는 총 6자리의 숫자로 이루어져야 합니다.");
	}

	@DisplayName("정적 팩토리 메소드 테스트 - 6자리 미만인 경우")
	@Test
	void checkValidationWhenUnderNumbersSize() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			LottoTicket.of("1, 2, 3, 4, 5")
		).withMessage("로또는 총 6자리의 숫자로 이루어져야 합니다.");
	}

	@DisplayName("정적 팩토리 메소드 테스트 - 문자가 입력 된 경우")
	@Test
	void checkValidationWhenNotNumber() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			LottoTicket.of("w, r, o, o, n, g")
		).withMessage("로또 숫자는 문자가 될 수 없습니다.");
	}

	@DisplayName("정적 팩토리 메소드 테스트 - 중복이 있는 경우")
	@Test
	void checkValidationWhenDuplicateNumber() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			LottoTicket.of("1, 1, 2, 3, 4, 5")
		).withMessage("로또 숫자는 중복 될 수 없습니다.");
	}

	@DisplayName("정적 팩토리 메소드 테스트 - 0이하의 숫자가 존재 하는 경우")
	@Test
	void checkValidationWhenEachUnderRange() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			LottoTicket.of("0, 1, 23, 34, 22, 3")
		).withMessage("로또 숫자는 0이하 일 수 없습니다.");
	}

	@DisplayName("정적 팩토리 메소드 테스트 - 45를 초과하는 숫자가 존재 하는 경우")
	@Test
	void checkValidationWhenEachOverRange() {
		assertThatIllegalArgumentException().isThrownBy(() ->
			LottoTicket.of("46, 47, 48, 49, 1, 2")
		).withMessage("로또 숫자는 45를 넘기면 안됩니다.");
	}


	@DisplayName("getter 테스트")
	@Test
	void checkGetNumbers() {
		List<LottoNumber> lottoNumbers = new ArrayList<>();
		lottoNumbers.add(new LottoNumber("1"));
		lottoNumbers.add(new LottoNumber("2"));
		lottoNumbers.add(new LottoNumber("13"));
		lottoNumbers.add(new LottoNumber("14"));
		lottoNumbers.add(new LottoNumber("21"));
		lottoNumbers.add(new LottoNumber("34"));

		LottoTicket lottoTicket = LottoTicket.of(lottoNumbers);
		List<String> expected = Arrays.asList("1", "2", "13", "14", "21", "34");
		List<String> actual = lottoTicket.getNumbers();

		assertThat(actual).isEqualTo(expected);
	}

	@DisplayName("로또 번호 일치 개수 테스트")
	@Test
	void checkGetMatchCountWhenAllMatch() {
		List<LottoNumber> lottoNumbers =
			Arrays.asList(
				new LottoNumber("1"),
				new LottoNumber("2"),
				new LottoNumber("3"),
				new LottoNumber("4"),
				new LottoNumber("5"),
				new LottoNumber("6")
			);

		LottoTicket lottoTicket = LottoTicket.of(lottoNumbers);
		WinningNumbers winningNumbers = new WinningNumbers("1, 2, 3, 4, 5, 6", "7");
		assertThat(lottoTicket.getMatchCount(winningNumbers)).isEqualTo(6);
	}

	@DisplayName("보너스 번호 일치 여부 테스트")
	@Test
	void checkIsBonusNotMatchWhenSecondPrizeState() {
		List<LottoNumber> lottoNumbers =
			Arrays.asList(
				new LottoNumber("1"),
				new LottoNumber("2"),
				new LottoNumber("3"),
				new LottoNumber("4"),
				new LottoNumber("5"),
				new LottoNumber("6")
			);

		LottoTicket lottoTicket = LottoTicket.of(lottoNumbers);
		WinningNumbers winningNumbers = new WinningNumbers("1, 2, 3, 4, 5, 7", "6");

		assertThat(lottoTicket.isBonusNotMatch(winningNumbers)).isFalse();
	}
}
