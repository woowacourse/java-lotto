package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

	private static List<Lotto> buyAuto(LottoBuyCount lottoBuyCount) {
		return buy(lottoBuyCount, LottoStore::createRandomLotto);
	}

	private static List<Lotto> buyManual(LottoBuyCount lottoBuyCount, List<String> manual) {
		return buy(lottoBuyCount, () -> LottoFactory.createLotto(manual.remove(0)));
	}

	public static List<Lotto> buyAutoAndManual(LottoBuyCount autoBuyCount, LottoBuyCount manualBuyCount,
			List<String> manual) {
		return Stream.concat(buyManual(manualBuyCount, manual).stream(), buyAuto(autoBuyCount).stream())
				.collect(Collectors.toList());
	}

	private static List<Lotto> buy(LottoBuyCount lottoBuyCount, Supplier<Lotto> creator) {
		List<Lotto> lottos = new ArrayList<>();
		lottoBuyCount.forEachRemaining(count -> lottos.add(creator.get()));
		return lottos;
	}
}
