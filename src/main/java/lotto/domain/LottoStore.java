package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoStore {
	private static Lotto createRandomLotto() {
		List<LottoNumber> shuffledLottoNumbers = LottoNumber.values()
				.stream()
				.collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
					Collections.shuffle(list);
					return list;
				}));
		return new Lotto(shuffledLottoNumbers.subList(0, Lotto.SIZE));
	}

	public static List<Lotto> buy(LottoPurchaseMoney lottoPurchaseMoney) {
		List<Lotto> lottos = new ArrayList<>();
		lottoPurchaseMoney.forEachRemaining(money -> {
			lottos.add(createRandomLotto());
		});
		return lottos;
	}

	public static List<Lotto> buy(LottoPurchaseMoney lottoPurchaseMoney, List<String> manual) {
		lottoPurchaseMoney.spend(manual.size());
		return manual.stream().
				map(LottoFactory::create)
				.collect(Collectors.toList());
	}
}
