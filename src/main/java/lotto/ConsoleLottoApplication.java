package lotto;

import lotto.domain.number.*;
import lotto.domain.random.RandomNumberGenerator;
import lotto.domain.result.GameResult;
import lotto.domain.result.GameResults;
import lotto.domain.result.Yield;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class ConsoleLottoApplication {
    public static void main(String[] args) {
        // 구입 금액 입력
        PurchaseNumber purchaseNumber = InputView.inputPurchaseMoney();
        // 구입 개수 출력
        OutputView.printPurchaseNumber(purchaseNumber);

        // 랜덤한 번호를 담은 List 출력하기
        List<LottoNumbers> lottoNumbersList = new ArrayList<>();
        for (int i = 0; i < purchaseNumber.getPurchaseNumber(); i++) {
            RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
            List<LottoNumber> randomNumbers = randomNumberGenerator.generateNumbers();
            lottoNumbersList.add(new LottoNumbers(randomNumbers));
        }
        // 모든 로또를 담은 LottoNumbers 생성
        AllLottoNumbers allLottoNumbers = new AllLottoNumbers(lottoNumbersList);

        // 출력하는 로직
        OutputView.printAllLottoNumbers(allLottoNumbers);

        // 당첨번호, 보너스 볼 입력 로직
        WinningNumbers winningNumbers = InputView.inputWinningNumbers();

        // 당첨 통계 계산 로직
        List<GameResult> gameResultList = new ArrayList<>();
        for (int i = 0; i < purchaseNumber.getPurchaseNumber(); i++) {
            LottoNumbers presentLottoNumbers = allLottoNumbers.getAllLottoNumbers().get(i);
            int correctNumber = winningNumbers.calculateCollectNumberSize(presentLottoNumbers);
            boolean isCorrectBonusNumber = winningNumbers.isCorrectBonusNumber(presentLottoNumbers);
            GameResult gameResult = GameResult.calculateRank(correctNumber, isCorrectBonusNumber);
            gameResultList.add(gameResult);
        }
        GameResults gameResults = new GameResults(gameResultList);

        // 당첨 통계 출력
        OutputView.printGameResults(gameResults);

        // 수익률 계산
        Yield yield = Yield.calculateYield(purchaseNumber, gameResults);
        OutputView.printYield(yield);
    }
}
