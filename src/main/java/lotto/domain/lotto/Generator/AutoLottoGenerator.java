package lotto.domain.lotto.Generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;
import lotto.domain.lottonumber.LottoNumber;
import lotto.domain.lottonumber.LottoNumberCache;

public class AutoLottoGenerator implements LottosGenerator {
	private int countOfAutoLottos;

	public AutoLottoGenerator(int countOfAutoLottos) {
		this.countOfAutoLottos = countOfAutoLottos;
	}

	private Lotto createLotto() {
		List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumberCache.values());
		Collections.shuffle(lottoNumbers);
		return lottoNumbers.stream()
			.limit(Lotto.CORRECT_SIZE)
			.collect(Collectors.collectingAndThen(Collectors.toSet(), Lotto::new));
	}

	@Override
	public Lottos generate() {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < countOfAutoLottos; i++) {
			lottos.add(createLotto());
		}
		return new Lottos(lottos);
	}
}
