package com.woowacourse.lotto.domain.factory;

import java.util.ArrayList;
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
		List<LottoNumber> numbers = new ArrayList<>();
		for(int i=1; i<=6; ++i) {
			numbers.add(LottoNumber.getLottoNumber(i));
		}
		Lotto FirstLotto = new Lotto(numbers);

		numbers = new ArrayList<>();
		for(int i=7; i<=12; ++i) {
			numbers.add(LottoNumber.getLottoNumber(i));
		}
		Lotto SecondLotto = new Lotto(numbers);

		assertThat(new Lottos(lotto)).isEqualTo(new Lottos(Arrays.asList(FirstLotto, SecondLotto)));
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