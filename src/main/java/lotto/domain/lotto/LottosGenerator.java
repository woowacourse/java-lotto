package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoNumberCache;

public class LottosGenerator {
	public static Lottos generate(int numberOfLotto) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < numberOfLotto; i++) {
			lottos.add(createLotto());
		}
		return new Lottos(lottos);
	}

	private static Lotto createLotto() {
		List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumberCache.values());
		Collections.shuffle(lottoNumbers);
		return lottoNumbers.stream()
			.limit(Lotto.CORRECT_SIZE)
			.collect(Collectors.collectingAndThen(Collectors.toSet(), Lotto::new));
	}
}
