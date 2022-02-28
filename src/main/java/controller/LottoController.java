package controller;


import domain.Lotto.LottoNumberFactory;
import domain.Lotto.WinningLotto;
import domain.LottoGenerator.AutoLottoGenerator;
import domain.Result;
import domain.player.Money;
import domain.player.Player;
import dto.LottosDto;
import dto.RanksDto;
import view.InputView;
import view.OutputView;

import java.util.List;

public class LottoController {

    private Player player;
    private WinningLotto winningLotto;

    public void set() {
        player = new Player(new Money(InputView.inputPurchaseAmount()));
    }

    public void run(){
        player.purchaseLotto(new AutoLottoGenerator(), LottoNumberFactory.makeBoundary());
        OutputView.printPurchasedLotto(LottosDto.from(player.getLottos()));

        winningLotto = new WinningLotto(InputView.inputWinningNumber(), InputView.inputBonusBall());
        List<Result> results = player.judgeAll(winningLotto);

        OutputView.printResult(new RanksDto(player.calculateIncomeRate(results)));

    }
//
//    public LottosDto purchase(int purchaseAmount) {
//        player = new Player(new Money(purchaseAmount));
//        player.purchaseLotto(new AutoLottoGenerator(), LottoNumberFactory.makeBoundary());
//        return LottosDto.from(player.getLottos());
//    }
//
//    public Game run(){
//        {
//        LottosDto lottosDto = lottoController.purchase(InputView.inputPurchaseAmount());
//        OutputView.printPurchasedLotto(lottosDto);
//
//        lottoController.determineWinningNumber(InputView.inputWinningNumber(), InputView.inputBonusBall());
//
//        List<Result> results = lottoController.judgeLottos();
//        OutputView.printResult(lottoController.makeResult(results));
//    }
//    public List<Result> judgeLottos() {
//        return player.judgeAll(winningLotto);
//    }
//
//    public RanksDto makeResult(List<Result> judgeLottos) {
//        double totalIncome = Rank.calculateAllResult(judgeLottos);
//        double incomeRate = player.calculateIncomeRate(totalIncome);
//
//        return new RanksDto(incomeRate);
//    }
}
