package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {

	private final List<LottoNumber> lottoNumbers;

	private LottoMachine() {
		lottoNumbers = new ArrayList<>();
		for (int i = LottoNumber.MIN; i <= LottoNumber.MAX; i++) {
			lottoNumbers.add(new LottoNumber(i));
		}
	}

	public static LottoMachine getInstance() {
		return LottoMachineSingletonHolder.instance;
	}

	public List<Lotto> makeRandomLottos(LottoCount lottoCount) {
		List<Lotto> lottos = new ArrayList<>();

		for (int i = 0; i < lottoCount.getAutoLottoCount(); i++) {
			lottos.add(pickRandomBalls());
		}
		return lottos;
	}

	private Lotto pickRandomBalls() {
		Collections.shuffle(lottoNumbers);
		return new Lotto(lottoNumbers.stream()
				.limit(Lotto.SIZE)
				.sorted()
				.collect(Collectors.toList()));
	}

	public Lotto pickDedicatedBalls(List<Integer> LottoNumbers) {
		return new Lotto(LottoNumbers.stream()
				.map(this::pickBall)
				.collect(Collectors.toList()));
	}

	public LottoNumber pickBall(int number) {
		return lottoNumbers.stream()
				.filter(l -> l.isEqualTo(number))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(LottoNumber.LOTTO_NUMBER_OUTOF_BOUND));
	}

	private static class LottoMachineSingletonHolder {
		private static final LottoMachine instance = new LottoMachine();
	}
}
