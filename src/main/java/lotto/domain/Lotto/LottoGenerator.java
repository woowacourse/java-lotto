package lotto.domain.Lotto;

import lotto.domain.LottoNumber.LottoNumber;

import java.util.*;
import java.util.stream.Collectors;

public class LottoGenerator {
	public static Lotto generate() {
		List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.values());
		Collections.shuffle(lottoNumbers);
		return lottoNumbers.stream()
				.limit(Lotto.CORRECT_SIZE)
				.collect(Collectors.collectingAndThen(Collectors.toSet(), Lotto::new));
	}
}
