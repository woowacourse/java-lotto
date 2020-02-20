package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {
	private static final int START_LOTTO_NO = 1;
	private static final int END_LOTTO_NO = 45;
	private static final int LOTTO_FROM_INDEX = 0;
	private static final int LOTTO_TO_INDEX = 6;

	private static List<LottoNo> lottoBox = new ArrayList<>();

	static {
		for (int count = START_LOTTO_NO; count <= END_LOTTO_NO; count++) {
			lottoBox.add(new LottoNo(count));
		}
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
