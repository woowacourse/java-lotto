package lotto.domain;

import static lotto.domain.LottoNumberParser.*;
import static lotto.view.ConsoleInputView.*;

public class ManualLottoPurchaseStrategy implements LottoPurchaseStrategy {
	@Override
	public Lotto generate() {
		return new Lotto(parseToLottoNumberSet(inputManualLotto()));
	}
}
