package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AutoLottosGenerator implements LottosGenerator {
	public Lotto create() {
		List<LottoNumber> shuffledLottoNumbers = LottoNumber.values()
				.stream()
				.collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
					Collections.shuffle(list);
					return list;
				}));
		return new Lotto(shuffledLottoNumbers.subList(0, Lotto.SIZE));
	}

	@Override
	public Lottos generate(int count) {
		List<Lotto> lottos = new ArrayList<>();
		while (count-- > 0) {
			lottos.add(create());
		}
		return new Lottos(lottos);
	}
}
