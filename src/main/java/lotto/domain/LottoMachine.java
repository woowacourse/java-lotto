package lotto.domain;

import java.util.List;

public class LottoMachine {
	private final Money money;
	private final LottoQuantity manualLottoQuantity;
	private final LottoQuantity autoLottoQuantity;

	public LottoMachine(Money money, LottoQuantity manualLottoQuantity) {
		money.validateAffordabilityOf(manualLottoQuantity);
		this.money = money.getMoneyActuallyInvested();
		this.manualLottoQuantity = manualLottoQuantity;

		Money moneyLeft = money.getChangeAfterBuying(this.manualLottoQuantity);
		this.autoLottoQuantity = moneyLeft.getAffordableLottoQuantity();
	}

	public Lottos createLottosFrom(List<int[]> manualLottoNumbersSequence) {
		Lottos manualLottos = manualLottoQuantity.createLottosWith(new ManualLottoGenerator(manualLottoNumbersSequence));
		Lottos autoLottos = autoLottoQuantity.createLottosWith(new AutomaticLottoGenerator());

		return manualLottos.merge(autoLottos);
	}

	public Money getMoney() {
		return money;
	}

	public int getManualLottoQuantityAsInt() {
		return manualLottoQuantity.getLottoQuantity();
	}

	public int getAutoLottoQuantityAsInt() {
		return autoLottoQuantity.getLottoQuantity();
	}
}
