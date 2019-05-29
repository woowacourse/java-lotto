package com.woowacourse.lotto.view;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.Lottos;
import com.woowacourse.lotto.domain.Money;

public class OutputView {
	public static void printLotto(Lottos generateLotto) {
		for(Lotto lotto : generateLotto.getLottos()) {
			System.out.println(lotto);
		}
	}

	public static void printNumberOfLotto(Money money) {
		System.out.printf(UserOutput.PRINT_NUMBER_OF_LOTTO.getUserOutputMessage(), money.getCountOfLotto());
	}
}
