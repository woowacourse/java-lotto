package domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.LottoInputException;

public class LottoGame {
	private static final int DEFAULT_VALUE = 0;
	private static final int ONE = 1;
	private final List<Lotto> lottos;

	public LottoGame(Money purchaseMoney) {
		lottos = LottoFactory.createLottos(purchaseMoney);
	}

	public GameResult play(String inputSixNumbers, String inputBonusNumber) {
		Lotto winningNumbers = new Lotto(inputSixNumbers);
		LottoNumber bonusNumber = LottoNumber.createNumber(inputBonusNumber);
		bonusNumberValidate(winningNumbers, bonusNumber);

		return lottosCompare(winningNumbers, bonusNumber);
	}

	private void bonusNumberValidate(Lotto winningNumbers, LottoNumber lottoNumber) {
		if (winningNumbers.isContains(lottoNumber)) {
			throw new LottoInputException("로또번호와 보너스번호가 중복됩니다.");
		}
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
