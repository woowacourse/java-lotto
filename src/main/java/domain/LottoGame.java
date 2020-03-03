package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import exception.LackOfMoneyException;
import exception.LottoNumberDuplicateException;

public class LottoGame {
	private static final int DEFAULT_VALUE = 0;
	private static final int COUNT = 1;
	private static final int LOTTO_PRICE = 1000;

	private final int selfNumberLottoAmount;
	private final int autoNumberLottoAmount;
	private List<Lotto> lottos;
	private Lotto winningNumbers;
	private LottoNumber bonusNumber;

	public LottoGame(Money purchaseMoney, int selfNumberLottoAmount) {
		validateAmount(purchaseMoney, selfNumberLottoAmount);
		this.selfNumberLottoAmount = selfNumberLottoAmount;
		this.autoNumberLottoAmount = (int)(purchaseMoney.division(LOTTO_PRICE) - selfNumberLottoAmount);
		this.lottos = new ArrayList<>();
	}

	public void drawSelfLottos(List<String> inputSelfNumbers) {
		List<Lotto> selfLottos = LottoFactory.createSelfLottos(inputSelfNumbers);
		lottos.addAll(selfLottos);
	}

	private void validateAmount(Money purchaseMoney, int selfNumberLottoAmount) {
		int selfLottosPrice = selfNumberLottoAmount * LOTTO_PRICE;
		if (purchaseMoney.isLessThan(selfLottosPrice)) {
			throw new LackOfMoneyException();
		}
	}

	public int selfLottoAmount() {
		return selfNumberLottoAmount;
	}

	public void drawAutoLottos() {
		List<Lotto> autoLottos = LottoFactory.createAutoLottos(autoNumberLottoAmount);
		lottos.addAll(autoLottos);
	}

	public void play(List<Integer> inputSixNumbers, int inputBonusNumber) {
		validateDuplication(inputSixNumbers, inputBonusNumber);
		winningNumbers = new Lotto(inputSixNumbers
			.stream()
			.map(LottoNumber::createNumber)
			.collect(Collectors.toList()));
		bonusNumber = LottoNumber.createNumber(inputBonusNumber);
	}

	private void validateDuplication(List<Integer> inputSixNumbers, int inputBonusNumber) {
		if (inputSixNumbers.contains(inputBonusNumber)) {
			throw new LottoNumberDuplicateException();
		}
	}

	public void addRanks(Map<Rank, Integer> ranks) {
		for (Lotto lotto : lottos) {
			Rank rank = lotto.compare(winningNumbers, bonusNumber);
			ranks.put(rank, ranks.getOrDefault(rank, DEFAULT_VALUE) + COUNT);
		}
	}

	public List<Lotto> getLottos() {
		return lottos;
	}

	public int getSelfNumberLottoAmount() {
		return selfNumberLottoAmount;
	}

	public int getAutoNumberLottoAmount() {
		return autoNumberLottoAmount;
	}
}
