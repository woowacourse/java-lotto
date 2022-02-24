package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;

public class LottoMachine {
	private static final LottoMachine INSTANCE = new LottoMachine();
	private static final List<LottoNumber> LOTTO_BUCKET = new ArrayList<>();
	private static final int MIN_BOUND = 1;
	private static final int MAX_BOUND = 45;

	static {
		IntStream.rangeClosed(MIN_BOUND, MAX_BOUND)
			.mapToObj(LottoNumber::new)
			.forEach(LOTTO_BUCKET::add);
	}

	private LottoMachine() {
	}

	public static LottoMachine getInstance() {
		return INSTANCE;
	}

	public Lottos createLottos(int lottoCount) {
		List<Lotto> lottos = new ArrayList<>();
		for (int count = 0; count < lottoCount; count++) {
			lottos.add(createAutoLotto());
		}
		return new Lottos(lottos);
	}

	private Lotto createAutoLotto() {
		Collections.shuffle(LOTTO_BUCKET);
		return new Lotto(LOTTO_BUCKET.stream()
			.limit(6)
			.sorted()
			.collect(Collectors.toUnmodifiableList()));
	}
}
