package lotto.domain;

import java.util.ArrayList;
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
		Lottos manualLottos = create(new ManualLottoGenerator(manualLottoNumbersSequence), manualLottoQuantity);
		Lottos autoLottos = create(new AutomaticLottoGenerator(), autoLottoQuantity);

		return manualLottos.merge(autoLottos);
	}

	private Lottos create(LottoGenerator lottoGenerator, LottoQuantity lottoQuantity) {
		List<Lotto> lottos = new ArrayList<>();
		for (int i = 0; i < lottoQuantity.getLottoQuantity(); i++) {
			lottos.add(lottoGenerator.createLotto());
		}
		return new Lottos(lottos);
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
