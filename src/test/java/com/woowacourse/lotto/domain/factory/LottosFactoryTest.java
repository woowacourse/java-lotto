package com.woowacourse.lotto.domain.factory;

import java.util.Arrays;

import com.woowacourse.lotto.domain.Lottos;
import com.woowacourse.lotto.domain.Money;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottosFactoryTest {
	@Test
	void checkSizeOfLottos() {
 		Lottos lottos = new LottosFactory(new Money(3000), 2)
				.generateLotto(Arrays.asList("1,2,3,4,5,6", "1,2,3,4,5,6"));
		assertTrue(lottos.size() == 3);
	}
}
