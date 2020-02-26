package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import exception.LottoNumberDuplicateException;

public class LottoGame {
	private static final int DEFAULT_VALUE = 0;
	private static final int ONE = 1;
	private final List<Lotto> lottos;
	private Lotto winningNumbers;
	private LottoNumber bonusNumber;

	public LottoGame(Money purchaseMoney) {
		lottos = LottoFactory.createLottos(purchaseMoney);
	}

	public void play(String[] inputSixNumbers, String inputBonusNumber) {
		duplicationValidate(inputSixNumbers, inputBonusNumber);
		winningNumbers = new Lotto(inputSixNumbers);
		bonusNumber = LottoNumber.createNumber(inputBonusNumber);
	}

	private void duplicationValidate(String[] inputSixNumbers, String inputBonusNumber) {
		if (isContains(inputSixNumbers, inputBonusNumber)) {
			throw new LottoNumberDuplicateException();
		}
	}

	private boolean isContains(String[] inputSixNumbers, String inputBonusNumber) {
		return Arrays.asList(inputSixNumbers).contains(inputBonusNumber);
	}

	public void addRanks(Map<Rank, Integer> ranks) {
		for (Lotto lotto : lottos) {
			Rank rank = lotto.compare(winningNumbers, bonusNumber);
			ranks.put(rank, ranks.getOrDefault(rank, DEFAULT_VALUE) + ONE);
		}
	}

	public List<Lotto> getLottos() {
		return lottos;
	}
}
