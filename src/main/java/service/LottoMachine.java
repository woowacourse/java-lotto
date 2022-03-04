package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;

public class LottoMachine {
	private static final List<LottoNumber> LOTTO_BUCKET = LottoNumber.ofList();

	public static Lottos createLottos(int lottoCount) {
		List<Lotto> lottos = new ArrayList<>();
		for (int count = 0; count < lottoCount; count++) {
			lottos.add(createAutoLotto());
		}
		return new Lottos(lottos);
	}

	public static Lottos createManualAndAutoMixLottos(Lottos manualLottos, int lottoCount) {
		List<Lotto> lottos = new ArrayList<>(manualLottos.getLottos());
		for (int count = 0; count < lottoCount; count++) {
			lottos.add(createAutoLotto());
		}
		return new Lottos(lottos);
	}

	private static Lotto createAutoLotto() {
		Collections.shuffle(LOTTO_BUCKET);
		return new Lotto(LOTTO_BUCKET.stream()
			.limit(Lotto.LOTTO_SIZE)
			.collect(Collectors.toUnmodifiableList()));
	}
}
