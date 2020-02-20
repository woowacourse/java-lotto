package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoFactory {
	private static final int LOTTO_FROM_INDEX = 0;
	private static final int LOTTO_TO_INDEX = 6;
	private static final List<LottoNo> lottoBox;

	static {
		lottoBox = IntStream.range(LottoNo.MIN_LOTTO_NO, LottoNo.MAX_LOTTO_NO)
			.boxed()
			.map(LottoNo::new)
			.collect(Collectors.toList());
	}

	public static List<Lotto> createLotteries(Money money) {
		List<Lotto> result = new ArrayList<>();
		int count = money.divideThousand();
		for (int i = 0; i < count; i++) {
			result.add(createLotto());
		}
		return result;
	}

	private static Lotto createLotto() {
		List<LottoNo> lotto = pickAutoRandomNumber();
		Collections.sort(lotto);
		return new Lotto(lotto);
	}

	private static List<LottoNo> pickAutoRandomNumber() {
		List<LottoNo> lotto = new ArrayList<>();
		lotto.addAll(lottoBox);
		Collections.shuffle(lotto);
		lotto = lotto.subList(LOTTO_FROM_INDEX, LOTTO_TO_INDEX);
		return lotto;
	}
}
