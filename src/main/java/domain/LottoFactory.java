package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoFactory {
	private static final List<LottoNumber> numbers = new ArrayList<>();

	static {
		for (int i = 1; i <= 45; i++) {
			numbers.add(new LottoNumber(i));
		}
	}

	public static List<Lotto> createLottos(int purchaseMoney) {
		int lottoCount = purchaseMoney / 1000;
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < lottoCount; i++) {
			lottos.add(createLotto());
		}
		return lottos;
	}

	private static Lotto createLotto() {
		Collections.shuffle(numbers);
		ArrayList<LottoNumber> subNumbers = new ArrayList<>(numbers.subList(0, 6));
		return new Lotto(subNumbers);
	}
}
