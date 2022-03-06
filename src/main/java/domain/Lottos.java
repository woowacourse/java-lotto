package domain;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public class Lottos {
	private final List<Lotto> lottos;

	public Lottos(List<Lotto> lottos) {
		Objects.requireNonNull(lottos, "[ERROR] lottos가 null 입니다.");
		this.lottos = new ArrayList<>(lottos);
	}

	public Map<Rank, Long> countRank(WinningLotto winningLotto) {
		return 	lottos.stream()
			.map(winningLotto::calculateRank)
			.filter(rank -> !rank.isNothing())
			.collect(groupingBy(Function.identity(), counting()));
	}

	public List<Lotto> getLottos() {
		return new ArrayList<>(lottos);
	}

	public int getSize() {
		return lottos.size();
	}
}
