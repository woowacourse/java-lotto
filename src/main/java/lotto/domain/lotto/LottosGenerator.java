package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoNumberCache;

public class LottosGenerator {
	public static Lottos generateLottos(int numberOfLotto) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < numberOfLotto; i++) {
			lottos.add(generateLotto());
		}
		return new Lottos(lottos);
	}

	private static Lotto generateLotto() {
		List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumberCache.values());
		Collections.shuffle(lottoNumbers);
		return lottoNumbers.stream()
			.limit(Lotto.CORRECT_SIZE)
			.collect(Collectors.collectingAndThen(Collectors.toSet(), Lotto::new));
	}
}
