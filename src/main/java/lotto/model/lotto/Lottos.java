package lotto.model.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lotto.model.prize.MatchResult;

public class Lottos {

	private final List<Lotto> lottos;

	private Lottos(List<Lotto> lottos) {
		this.lottos = lottos;
	}

	public static Lottos purchase(int totalCount, int manualCount, List<List<String>> manualLottoNumbers) {
		int autoCount = getAutoCount(totalCount, manualCount);
		List<Lotto> lottos = new ArrayList<>();
		generateLottos(autoCount, manualLottoNumbers, lottos);

		return new Lottos(lottos);
	}

	private static int getAutoCount(int totalCount, int manualCount) {
		return totalCount - manualCount;
	}

	private static void generateLottos(int autoCount, List<List<String>> input, List<Lotto> lottos) {
		generateManualLotto(input, lottos);
		generateAutoLotto(autoCount, lottos);
	}

	private static void generateManualLotto(List<List<String>> input, List<Lotto> lottos) {
		input.forEach(
			numbers -> lottos.add(Lotto.fromManual(numbers))
		);
	}

	private static void generateAutoLotto(int autoCount, List<Lotto> lottos) {
		IntStream.range(0, autoCount).mapToObj(index -> Lotto.fromAuto()).forEach(lottos::add);
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
