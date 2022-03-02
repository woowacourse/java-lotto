package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lotto.model.number.BonusBall;
import lotto.model.number.WinningBalls;
import lotto.model.prize.MatchResult;

public class Lottos {

	private final List<AutoLotto> autoLottos;

	private Lottos(List<AutoLotto> autoLottos) {
		this.autoLottos = autoLottos;
	}

	public static Lottos purchase(int count) {
		List<AutoLotto> autoLottos = new ArrayList<>();

		for (int i = 0; i < count; i++) {
			autoLottos.add(new AutoLotto(AutoLotto.selectNumbers()));
		}

		return new Lottos(autoLottos);
	}

	public List<MatchResult> match(WinningBalls winningBalls, BonusBall bonusBall) {
		return this.autoLottos.stream()
				.map(lotto -> MatchResult.of(lotto, winningBalls, bonusBall))
				.collect(Collectors.toList());
	}

	public List<AutoLotto> getLottos() {
		return this.autoLottos;
	}
}
