package lotto.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lotto.model.ball.BonusBall;
import lotto.model.ball.WinningBalls;
import lotto.model.prize.MatchResult;

public class Lottos {

	private final List<Lotto> lottos;

	private Lottos(List<Lotto> lottos) {
		this.lottos = lottos;
	}

	public static Lottos purchase(int totalCount, int manualCount, List<String[]> manualLottoNumbers) {
		int autoCount = totalCount - manualCount;
		List<Lotto> lottos = new ArrayList<>();
		generateLottos(autoCount, manualLottoNumbers, lottos);

		return new Lottos(lottos);
	}

	private static void generateLottos(int autoCount, List<String[]> input, List<Lotto> lottos) {
		generateManualLotto(input, lottos);
		generateAutoLotto(autoCount, lottos);
	}

	private static void generateManualLotto(List<String[]> input, List<Lotto> lottos) {
		input.forEach(
			numbers -> lottos.add(new Lotto(Lotto.generateManual(numbers)))
		);
	}

	private static void generateAutoLotto(int autoCount, List<Lotto> lottos) {
		for (int index = 0; index < autoCount; index++) {
			lottos.add(new Lotto(Lotto.generateAuto()));
		}
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
