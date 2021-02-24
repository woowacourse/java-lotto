package lotto.domain;

import java.util.List;
import java.util.Objects;

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
		ManualLottoGenerator manualLottoGenerator = new ManualLottoGenerator(manualLottoNumbersSequence);
		Lottos manualLottos = manualLottoQuantity.createLottosUsing(manualLottoGenerator);

		AutomaticLottoGenerator automaticLottoGenerator = new AutomaticLottoGenerator();
		Lottos autoLottos = autoLottoQuantity.createLottosUsing(automaticLottoGenerator);

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LottoMachine that = (LottoMachine) o;
		return money.equals(that.money) && manualLottoQuantity.equals(that.manualLottoQuantity) && autoLottoQuantity.equals(that.autoLottoQuantity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(money, manualLottoQuantity, autoLottoQuantity);
	}
}
