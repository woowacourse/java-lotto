package domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

	private final List<LottoNumber> lottoLottoNumbers = Stream.of(1, 5, 9, 11, 24, 36)
		.map(LottoNumber::new)
		.collect(Collectors.toList());

	@Test
	void checkIsContain() {
		//given
		int actualNumber = 24;
		//when
		LottoNumber inclusionExpectedLottoNumber = new LottoNumber(actualNumber);
		Lotto lotto = new Lotto(lottoLottoNumbers);
		//then
		assertTrue(lotto.isContain(inclusionExpectedLottoNumber));
	}

	@Test
	void checkConfirmWinningResult() {
		//given
		Lotto lotto = new Lotto(lottoLottoNumbers);
		Lotto winningLotto = new Lotto(lottoLottoNumbers);
		LottoNumber bonusLottoNumber = new LottoNumber(2);

		//when
		LottoRank lottoRank = lotto.confirmWinningResult(winningLotto, bonusLottoNumber);

		//then
		assertThat(lottoRank).isEqualTo(LottoRank.FIRST);
	}

	@Test
	@DisplayName("로또 팩토리는 랜덤한 숫자 6개를 뽑아야한다.")
	void checkLottoSize_1() {
		//given
		List<LottoNumber> missLotto = Stream.of(1, 5, 9, 11, 18)
			.map(LottoNumber::new)
			.collect(Collectors.toList());

		//then
		assertThatThrownBy(() -> new Lotto(missLotto))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("로또 번호는 6개의 숫자여야 합니다");
	}

	@Test
	@DisplayName("로또 팩토리는 랜덤한 숫자 6개를 뽑아야한다.")
	void checkLottoSize_2() {
		//given
		List<LottoNumber> missLotto = Stream.of(1, 5, 9, 11, 18, 22, 30)
			.map(LottoNumber::new)
			.collect(Collectors.toList());

		//then
		assertThatThrownBy(() -> new Lotto(missLotto))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("로또 번호는 6개의 숫자여야 합니다");
	}

	@Test
	@DisplayName("로또 번호는 중복된 숫자가 존재할 수 없다.")
	void checkDuplicateLottoNumber() {
		//given
		List<LottoNumber> duplicateLotto = Stream.of(1, 5, 9, 11, 11, 5)
			.map(LottoNumber::new)
			.collect(Collectors.toList());

		//then
		assertThatThrownBy(() -> new Lotto(duplicateLotto))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("중복된 숫자를 입력할 수 없습니다");
	}

}