package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lottos {

	public static final int LOTTO_NUMBER_SIZE = 6;

	private final List<Lotto> lottos = new ArrayList<>();

	public Lottos(int lottoCount) {
		for (int i = 0; i < lottoCount; i++) {
			lottos.add(createLotto());
		}
	}

	private Lotto createLotto() {
		Set<LottoNumber> lottoNumbers = new HashSet<>();

		while (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
			lottoNumbers.add(LottoNumber.createByRandom());
		}

		return new Lotto(new ArrayList<>(lottoNumbers));
	}
}
