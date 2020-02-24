package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoNumberCache;

public class LottoGenerator {
	public static Lotto generate() {
		List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumberCache.values());
		Collections.shuffle(lottoNumbers);
		return lottoNumbers.stream()
				.limit(Lotto.CORRECT_SIZE)
				.collect(Collectors.collectingAndThen(Collectors.toSet(), Lotto::new));
	}
}
