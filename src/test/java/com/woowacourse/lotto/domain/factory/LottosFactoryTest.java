package com.woowacourse.lotto.domain.factory;

import java.util.Arrays;

import com.woowacourse.lotto.domain.Lottos;
import com.woowacourse.lotto.domain.LottoMoney;
import com.woowacourse.lotto.exception.InvalidCountOfManualLottoException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LottosFactoryTest {
	@Test
	void checkSizeOfLottos() {
		Lottos lottos = new LottosFactory(new LottoMoney(3000), 2)
				.generateLotto(Arrays.asList("1,2,3,4,5,6", "7,8,9,10,11,12"));
		assertTrue(lottos.size() == 3);
	}

	@Test
	void checkRangeCountOfManualLotto() {
		LottoMoney lottoMoney = new LottoMoney(3000);
		assertThrows(InvalidCountOfManualLottoException.class,
				() -> new LottosFactory(lottoMoney, -1));
		assertThrows(InvalidCountOfManualLottoException.class,
				() -> new LottosFactory(lottoMoney, 4));
	}
}
