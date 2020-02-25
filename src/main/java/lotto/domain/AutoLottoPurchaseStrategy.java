package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AutoLottoPurchaseStrategy implements LottoPurchaseStrategy {
	@Override
	public Lotto generate() {
		List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.values());
		Collections.shuffle(lottoNumbers);

		return new Lotto(lottoNumbers.stream()
			.limit(Lotto.CORRECT_SIZE)
			.collect(Collectors.toSet()));
	}
}
