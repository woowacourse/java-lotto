package domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.LottoNumberDuplicateException;

public class LottoGame {
	private static final int DEFAULT_VALUE = 0;
	private static final int ONE = 1;
	private final List<Lotto> lottos;

	public LottoGame(Money purchaseMoney) {
		lottos = LottoFactory.createLottos(purchaseMoney);
	}

	public GameResult play(String[] inputSixNumbers, String inputBonusNumber) {
		duplicationValidate(inputSixNumbers, inputBonusNumber);
		Lotto winningNumbers = new Lotto(inputSixNumbers);
		LottoNumber bonusNumber = LottoNumber.createNumber(inputBonusNumber);

		return lottosCompare(winningNumbers, bonusNumber);
	}

	private void duplicationValidate(String[] inputSixNumbers, String inputBonusNumber) {
		if (isContains(inputSixNumbers, inputBonusNumber)) {
			throw new LottoNumberDuplicateException();
		}
	}

	private boolean isContains(String[] inputSixNumbers, String inputBonusNumber) {
		return Arrays.asList(inputSixNumbers).contains(inputBonusNumber);
	}

	private GameResult lottosCompare(Lotto winningNumbers, LottoNumber bonusNumber) {
		Map<Rank, Integer> ranks = new HashMap<>();
		for (Lotto lotto : lottos) {
			Rank rank = lotto.compare(winningNumbers, bonusNumber);
			ranks.put(rank, ranks.getOrDefault(rank, DEFAULT_VALUE) + ONE);
		}
		return new GameResult(ranks);
	}

	public List<Lotto> getLottos() {
		return lottos;
	}
}
