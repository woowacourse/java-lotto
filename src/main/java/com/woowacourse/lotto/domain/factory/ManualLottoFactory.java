package com.woowacourse.lotto.domain.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.woowacourse.lotto.domain.Lotto;
import com.woowacourse.lotto.domain.LottoNumber;
import com.woowacourse.lotto.exception.InvalidCountOfManualLottoException;
import com.woowacourse.lotto.utils.StringSeparator;

import static com.woowacourse.lotto.domain.LottoNumber.NUMBER_OF_LOTTO;
import static com.woowacourse.lotto.domain.LottoNumber.VIOLATE_LOTTO_NUMBER_RANGE;

public class ManualLottoFactory implements LottoFactory {
	private final List<String> numbers;

	public ManualLottoFactory(final List<String> numbers) {
		this.numbers = new ArrayList<>(numbers);
		validateSizeOfLotto(numbers);
	}

	private void validateSizeOfLotto(List<String> numbers) {
		if (!numbers.stream().allMatch(s -> StringSeparator.splitString(s).size() == NUMBER_OF_LOTTO)) {
			throw new InvalidCountOfManualLottoException(VIOLATE_LOTTO_NUMBER_RANGE);
		}
	}

	@Override
	public List<Lotto> generateLotto() {
		List<Lotto> lotto = new ArrayList<>();
		for (String s : numbers) {
			List<LottoNumber> lottoNumbers = StringSeparator.splitString(s).stream()
					.map(number -> LottoNumber.getLottoNumber(Integer.valueOf(number)))
					.collect(Collectors.toList());
			Lotto newLotto = new Lotto(lottoNumbers);
			lotto.add(newLotto);
		}
		return lotto;
	}
}
