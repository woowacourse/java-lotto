package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoGame {

	public static final int LOTTO_NUMBER_SIZE = 6;
	public static final int LOTTO_PRICE = 1000;

	private final List<Lotto> lottos;

	public LottoGame(int money) {
		this.lottos = purchaseLottos(money);
	}

	private List<Lotto> purchaseLottos(int money) {
		final int lottoCount = money / LOTTO_PRICE;
		List<Lotto> lottos = new ArrayList<>();

		for (int i = 0; i < lottoCount; i++) {
			lottos.add(createLotto());
		}

		return lottos;
	}

	private Lotto createLotto() {
		Set<LottoNumber> lottoNumbers = new HashSet<>();

		while (lottoNumbers.size() != LOTTO_NUMBER_SIZE) {
			lottoNumbers.add(LottoNumber.createByRandom());
		}

		return new Lotto(new ArrayList<>(lottoNumbers));
	}
}
