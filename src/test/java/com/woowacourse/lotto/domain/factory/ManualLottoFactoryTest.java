package com.woowacourse.lotto.domain.factory;

import java.util.Arrays;
import java.util.List;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoNumber;
import com.woowacourse.lotto.domain.Lottos;
import com.woowacourse.lotto.exception.InvalidCountOfManualLottoException;
import com.woowacourse.lotto.exception.InvalidNumberException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ManualLottoFactoryTest {
	@Test
	void generateManualLotto() {
		List<Lotto> lotto = new ManualLottoFactory(2, Arrays.asList("1,2,3,4,5,6", "7,8,9,10,11,12")).generateLotto();
		Lotto lotto1 = new Lotto(Arrays.asList(LottoNumber.getLottoNumber(1), LottoNumber.getLottoNumber(2), LottoNumber.getLottoNumber(3),
				LottoNumber.getLottoNumber(4), LottoNumber.getLottoNumber(5), LottoNumber.getLottoNumber(6)));
		Lotto lotto2 = new Lotto(Arrays.asList(LottoNumber.getLottoNumber(7), LottoNumber.getLottoNumber(8), LottoNumber.getLottoNumber(9),
				LottoNumber.getLottoNumber(10), LottoNumber.getLottoNumber(11), LottoNumber.getLottoNumber(12)));
		assertThat(new Lottos(lotto)).isEqualTo(new Lottos(Arrays.asList(lotto1, lotto2)));
	}

	@Test
	void invalidNumberOfLotto() {
		assertThrows(InvalidNumberException.class, () -> new ManualLottoFactory(1, Arrays.asList("0,1,2,3,4,5")).generateLotto());
		assertThrows(InvalidNumberException.class, () -> new ManualLottoFactory(1, Arrays.asList("1,2,3,4,5,46")).generateLotto());
	}

	@Test
	void invalidSizeOfLotto() {
		assertThrows(InvalidCountOfManualLottoException.class, ()
				-> new ManualLottoFactory(1, Arrays.asList("1,2,3,4,5")).generateLotto());
	}

	@Test
	void duplicatedNumber() {
		assertThrows(InvalidNumberException.class, ()
				-> new ManualLottoFactory(1, Arrays.asList("1,2,3,4,5,5")).generateLotto());

	}
}