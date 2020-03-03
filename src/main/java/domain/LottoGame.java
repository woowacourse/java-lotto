package domain;

import java.util.Map;

public class LottoGame {
	private static final int DEFAULT_VALUE = 0;
	private static final int COUNT = 1;
	private final WinningLotto winningLotto;
	private final Lottos lottos;

	public LottoGame(Lottos lottos, WinningLotto winningLotto) {
		this.winningLotto = winningLotto;
		this.lottos = lottos;
	}

	public void addRanks(Map<Rank, Integer> ranks) {
		lottos.stream()
			.map(lotto -> lotto.compare(winningLotto))
			.forEach(rank -> ranks.put(rank, ranks.getOrDefault(rank, DEFAULT_VALUE) + COUNT));
	}
}
