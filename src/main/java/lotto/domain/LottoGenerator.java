package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoGenerator {
	public static Lotto generate() {
		List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.values());
		Collections.shuffle(lottoNumbers);

		return new Lotto(lottoNumbers.stream()
			.limit(Lotto.CORRECT_SIZE)
			.sorted()
			.collect(Collectors.toList()));
	}
}
