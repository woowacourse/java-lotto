package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {
	private static final int START_LOTTO_NO = 1;
	private static final int END_LOTTO_NO = 45;
	private static final int TICKET_FROM_INDEX = 0;
	private static final int TICKET_TO_INDEX = 6;

	private static List<Integer> lottoBox = new ArrayList<>();

	static {
		for (int count = START_LOTTO_NO; count <= END_LOTTO_NO; count++) {
			lottoBox.add(count);
		}
	}

	public static List<Lotto> createLotteries(int count) {
		List<Lotto> result = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			result.add(createLotto());
		}
		return result;
	}

	public static Lotto createLotto() {
		List<Integer> lotto = randomNoPick();
		Collections.sort(lotto);
		return new Lotto(lotto);
	}

	private static List<Integer> randomNoPick() {
		List<Integer> lotto = new ArrayList<>();
		lotto.addAll(lottoBox);
		Collections.shuffle(lotto);
		lotto = lotto.subList(TICKET_FROM_INDEX, TICKET_TO_INDEX);
		return lotto;
	}
}
