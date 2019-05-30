package com.woowacourse.lotto.domain.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoNumber;
import com.woowacourse.lotto.domain.Lottos;
import com.woowacourse.lotto.utils.StringSeparator;

public class ManualLottoFactory extends LottoFactory {
	private final List<String> numbers;

	public ManualLottoFactory(int numberOfLotto, final List<String> numbers) {
		super(numberOfLotto);
		this.numbers = numbers;
	}

	@Override
	public Lottos generateLotto() {
		List<Lotto> lotto = new ArrayList<>();
		for (String s : numbers) {
			lotto.add(new Lotto(StringSeparator.splitString(s).stream()
					.map(number -> LottoNumber.getLottoNumber(Integer.valueOf(number)))
					.collect(Collectors.toList())))
			;
		}
		return new Lottos(lotto);
	}
}
