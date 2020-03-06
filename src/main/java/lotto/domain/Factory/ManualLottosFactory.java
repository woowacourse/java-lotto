package lotto.domain.Factory;

import lotto.domain.Lotto;
import lotto.domain.LottoMoney;
import lotto.exceptions.LottoIllegalArgumentException;
import lotto.view.InputView;

import java.util.ArrayList;
import java.util.List;

public class ManualLottosFactory implements LottosFactory {
	private Lotto createOneManualLotto() {
		Lotto lotto;
		do {
			lotto = createOneManualLottoIfValid();
		} while (lotto == null);
		return lotto;
	}

	private Lotto createOneManualLottoIfValid() {
		try {
			return Lotto.of(InputView.inputManualLottoNumbers());
		} catch (LottoIllegalArgumentException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<Lotto> create(LottoMoney lottoMoney) {
		List<Lotto> manualLottos = new ArrayList<>();
		for (int i = 0; i < lottoMoney.countPurchasedTickets(); i++) {
			manualLottos.add(createOneManualLotto());
		}
		return manualLottos;
	}
}
