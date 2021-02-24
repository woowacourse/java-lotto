package lotto.controller;

import lotto.domain.user.User;
import lotto.domain.user.UserDto;
import lotto.model.Money;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    public void play() {
        User user = new User(Money.priceOf(InputView.inputPurchaseAmount()));
        user.buyManualLottos(InputView.inputManualLottoNumbers());
        user.buyAutomaticLottos();
        OutputView.printUserLottoPurchaseResult(UserDto.from(user));

//        Lotto winningLottoNumber = Lotto.of(InputView.inputWinningLottoNumbers());
//        int bonus = InputView.inputWinningBonus();
//        WinningLotto winningLotto = new WinningLotto(winningLottoNumber, bonus);
//        LottoResult lottoResult = purchasedLottos.match(winningLotto);
//        OutputView.printLottoResult(lottoResult);
    }
}


