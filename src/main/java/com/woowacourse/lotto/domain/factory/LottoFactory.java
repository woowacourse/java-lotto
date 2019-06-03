package com.woowacourse.lotto.domain.factory;

import java.util.List;

import com.woowacourse.lotto.domain.Lotto;

public interface LottoFactory {
	List<Lotto> generateLotto();
}
