package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Lottos {

	private final LottoGenerator lottoGenerator;
	private final List<Lotto> lottos;

	public Lottos(int count, LottoGenerator lottoGenerator) {
		this.lottoGenerator = lottoGenerator;
		this.lottos = generateLottos(count);
	}

	private List<Lotto> generateLottos(int count) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			lottos.add(new Lotto(lottoGenerator));
		}
		return lottos;
	}

	public List<Rank> getRanks(Balls answer, Ball bonusBall) {
		return lottos.stream()
			.map(lotto -> lotto.getRank(answer, bonusBall))
			.collect(Collectors.toUnmodifiableList());
	}

}
