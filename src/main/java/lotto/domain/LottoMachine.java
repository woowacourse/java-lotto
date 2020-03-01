package lotto.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoMachine {
	private static final int MAX_LOTTO_NUMBER = 45;
	private static final int MIN_LOTTO_NUMBER = 1;
	public static final String LOTTO_NUMBER_OUTOF_BOUNDS = "범위를 벗어난 로또 숫자입니다.";

	private final List<LottoNumber> lottoNumbers;

	private LottoMachine() {
		lottoNumbers = new ArrayList<>();
		for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
			lottoNumbers.add(new LottoNumber(i));
		}
	}

	public static LottoMachine getInstance() {
		return LottoMachineSingletonHolder.instance;
	}

	public List<Lotto> makeRandomLottos(LottoCount lottoCount) {
		List<Lotto> lottos = new ArrayList<>();

		for (int i = 0; i < lottoCount.getAutoLottoCount(); i++) {
			lottos.add(new Lotto(pickRandomBalls()));
		}
		return lottos;
	}

	private List<LottoNumber> pickRandomBalls() {
		Collections.shuffle(lottoNumbers);
		return lottoNumbers.stream()
				.limit(Lotto.SIZE)
				.sorted()
				.collect(Collectors.toList());
	}

	public List<LottoNumber> pickDedicatedBalls(List<Integer> LottoNumbers) {
		return LottoNumbers.stream()
				.map(this::pickBall)
				.collect(Collectors.toList());
	}

	public LottoNumber pickBall(int number) {
		return lottoNumbers.stream()
				.filter(l -> l.isEqualTo(number))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException(LOTTO_NUMBER_OUTOF_BOUNDS));
	}

	private static class LottoMachineSingletonHolder {
		private static final LottoMachine instance = new LottoMachine();
	}
}
