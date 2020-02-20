package controller;

import domain.*;
import domain.lottonumber.*;
import domain.lottonumber.generator.NumberGenerator;
import domain.lottonumber.generator.RandomNumberGenerator;
import domain.lottonumber.generator.UserNumberGenerator;
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

    /**
     * printResult는 로또 당첨번호와 기존 생성 로또번호를 비교한 결과(LottoResult)를 바탕으로 결과를 뷰에 보낸다.
     * 이 과정에서 각각의 등수(1등,2등...)에 따른 출력값을 printEachResult를 호출하여 뷰에 전달한다.
     * 이후 수익률을 출력하기 위해 기존에 금액을 저장한 money 클래스를 파라미터로 받아 재활용한다.
     *
     * @param money 수익률 확인을 위한 금액 클래스 이다.
     */
    private void printResult(Money money) {
        long earning = 0;
        OutputView.printResultTitle();
        for (LottoRank rank : LottoRank.values()) {
            printEachResult(rank);
            earning += lottoResult.get(rank).multiply(rank.getWinning());
        }
        OutputView.printEarning(money.calculateEarnings(earning));
    }

    /**
     * printEachResult는 Enum화되어있는 각각의 등수(LottoRank) 값에 따른 결과를 뷰에 보내준다.
     * 예를 들어, 1등이 있다면 그 둥수와 등수에 해당하는 사람의 수를 뷰에 보내준다.
     *
     * @param rank 몇 몇이나 있는지 확인할 등수이다. lottoResult의 키값으로 쓰인다.
     */
    private void printEachResult(LottoRank rank) {
        if (rank == LottoRank.NOTHING) {
            return;
        }
        OutputView.printResult(rank, lottoResult.get(rank));
    }

    private void makeWinnerNumbers() {
        UserNumberGenerator userNumberGenerator = new UserNumberGenerator();
        userNumberGenerator.init(InputView.inputWinnerNumbers());
        LottoNumbers winnerNumbers = LottoNumbersFactory.createLottoNumbers(userNumberGenerator);
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
