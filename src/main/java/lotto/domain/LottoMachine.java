package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {
	private static final int MAX_LOTTO_NUMBER = 45;
	private static final int MIN_LOTTO_NUMBER = 1;

	private final List<Integer> lottoBalls;

	public LottoMachine() {
		lottoBalls = new ArrayList<>();
		for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
			lottoBalls.add(i);
		}
	}

	public List<Lotto> makeRandomLottos(int lottoCount) {
		List<Lotto> lottos = new ArrayList<>();

		for (int i = 0; i < lottoCount; i++) {
			lottos.add(new Lotto(pickRandomBalls()));
		}
		return lottos;
	}

	private List<Integer> pickRandomBalls() {
		Collections.shuffle(lottoBalls);
		return lottoBalls.stream()
			.limit(6)
			.collect(Collectors.toList());
	}
}
