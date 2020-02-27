package lotto.domain.strategy;

import static lotto.domain.LottoNumberParser.*;
import static lotto.view.ConsoleInputView.*;

import lotto.domain.lotto.Lotto;

public class ManualLottoPurchaseStrategy implements LottoPurchaseStrategy {
	@Override
	public Lotto generate() {
		return new Lotto(parseToLottoNumberSet(inputManualLotto()));
	}
}
