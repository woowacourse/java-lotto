package controller;

import domain.*;
import domain.lottonumber.*;
import domain.lottonumber.generator.NumberGenerator;
import domain.lottonumber.generator.RandomNumberGenerator;
import domain.lottoresult.LottoRank;
import domain.lottoresult.LottoResult;
import domain.lottoresult.LottoWinner;
import view.InputView;
import view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class LottoController {
    List<LottoNumbers> lottoGame = new ArrayList<>();
    LottoWinner lottoWinner;
    LottoResult lottoResult = new LottoResult();

    public void run() {
        Money money = new Money(InputView.inputMoney());
        makeLottoNumbers(money.calculateGames());
        makeWinnerNumbers();
        makeResult();
        printResult(money);
    }

    private void makeResult() {
        for (LottoNumbers lotto : lottoGame) {
            lottoResult.add(lottoWinner.createRank(lotto));
        }
    }

    private void printResult(Money money) {
        long earning = 0;
        OutputView.printResultTitle();
        for (LottoRank rank : LottoRank.values()) {
            printEachResult(rank);
            earning += lottoResult.get(rank).multiply(rank.getWinning());
        }
        OutputView.printEarning(money.calculateEarnings(earning));
    }

    private void printEachResult(LottoRank rank) {
        if (rank == LottoRank.NOTHING) {
            return;
        }
        OutputView.printResult(rank, lottoResult.get(rank));
    }

    private void makeWinnerNumbers() {
        LottoNumbers winnerNumbers = LottoNumbersFactory.createLottoNumbers(InputView.inputWinnerNumbers());
        LottoNumber bonus = LottoNumber.of(InputView.inputBonusNumber());

        lottoWinner = new LottoWinner(winnerNumbers, bonus);
    }

    private void makeLottoNumbers(int repeat) {
        NumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        for (int i = 0; i < repeat; i++) {
            LottoNumbers lottoNumbers = LottoNumbersFactory.createLottoNumbers(randomNumberGenerator);
            OutputView.printLottoNumbers(lottoNumbers);
            lottoGame.add(lottoNumbers);
        }
    }
}
