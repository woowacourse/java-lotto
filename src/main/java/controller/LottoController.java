package controller;

import domain.*;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    Money money;
    List<LottoNumbers> lottoNumbers = new ArrayList<>();
    LottoWinner lottoWinner;
    LottoResult lottoResult = new LottoResult();

    public void run() {
        money = new Money(InputView.inputMoney());
        int repeat = money.calculateGames();
        NumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        for (int i = 0; i < repeat; i++) {
            LottoNumbers lottoNumberss = LottoNumbersFactory.createLottoNumbers(randomNumberGenerator);
            OutputView.printLottoNumbers(lottoNumberss);
            lottoNumbers.add(lottoNumberss);
        }
        UserNumberGenerator userNumberGenerator = new UserNumberGenerator();
        userNumberGenerator.input(InputView.inputWinnerNumbers());
        LottoNumbers winnerNumbers = LottoNumbersFactory.createLottoNumbers(userNumberGenerator);
        LottoNumber bonus = LottoNumber.of(InputView.inputBonusNumber());

        lottoWinner = new LottoWinner(winnerNumbers, bonus);
        for (LottoNumbers lotto : lottoNumbers) {
            lottoResult.add(lottoWinner.createRank(lotto));
        }
        long earning = 0;
        OutputView.printResultTitle();
        for (LottoRank rank : LottoRank.values()) {
            if (rank == LottoRank.NOTHING) {
                continue;
            }
            earning += lottoResult.get(rank).multiply(rank.getWinning());
            OutputView.printResult(rank, lottoResult.get(rank));
        }
        OutputView.printEarning(money.calculateEarnings(earning));
    }
}
