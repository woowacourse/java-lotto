package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lotto.model.number.BonusBall;
import lotto.model.number.WinningBalls;
import lotto.model.prize.MatchResult;

public class Lottos {

	private final List<Lotto> lottos;

	private Lottos(List<Lotto> lottos) {
		this.lottos = lottos;
	}

	public static Lottos purchase(int count) {
		List<Lotto> lottos = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			lottos.add(new Lotto(Lotto.selectNumbers()));
		}

		return new Lottos(lottos);
	}

	public List<MatchResult> match(WinningBalls winningBalls, BonusBall bonusBall) {
		return this.lottos.stream()
				.map(lotto -> MatchResult.of(lotto, winningBalls, bonusBall))
				.collect(Collectors.toList());
	}

	public List<Lotto> getLottos() {
		return this.lottos;
	}
}
