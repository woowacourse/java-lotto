package controller;


import domain.HitResult;
import domain.Lotto.LottoNumberFactory;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.AutoLottoGenerator;
import domain.LottoGenerator.CustomLottoGenerator;
import domain.Result;
import domain.player.Money;
import domain.player.Player;
import dto.LottosDto;
import dto.RanksDto;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {

    public void run() {
        Player player = new Player(new Money(InputView.inputPurchaseAmount()));
        int manualQuantity = InputView.inputManualQuantity();
        player.checkPossible(manualQuantity);
        List<List<Integer>> manualNumbers = InputView.inputManualNumber(manualQuantity);

        player.purchaseManualLotto(new CustomLottoGenerator(), manualNumbers);
        player.purchaseAutoLotto(new AutoLottoGenerator(), LottoNumberFactory.makeBoundary());
        OutputView.printPurchasedLotto(LottosDto.from(player.getLottos(), manualQuantity));

        WinningLotto winningLotto = new WinningLotto(InputView.inputWinningNumber(), InputView.inputBonusBall());
        List<Result> results = player.judgeAll(winningLotto);

        HitResult hitResult = new HitResult();
        OutputView.printResult(new RanksDto(player.calculateIncomeRate(results, hitResult), hitResult));

    }

}
