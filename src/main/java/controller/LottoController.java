package controller;

import java.util.ArrayList;
import java.util.List;
import model.BonusNumber;
import model.Lotto;
import model.Purchasement;
import model.WinningNumber;
import model.WinningResult;
import model.WinningStatus;
import view.InputView;
import view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Purchasement purchasement = inputView.readPurchaseAmount();
        int lottoCount = purchasement.calculateLottoCount();
        outputView.printLottoCount(lottoCount);

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoCount; i++) {
            lottos.add(new Lotto());
        }

        outputView.printLottos(lottos);

        WinningNumber winningNumber = inputView.readWinningNumbers();
        BonusNumber bonusNumber = inputView.readBonusNumbers(winningNumber);

        WinningResult winningResult = new WinningResult();
        for (Lotto lotto : lottos) {
            // 당첨 번호와의 일치 개수 구하기
            int matchingCount = winningNumber.findMatchingCountWith(lotto.getNumbers());
            boolean matchesBonusNumber = bonusNumber.matchesWith(lotto.getNumbers());
            WinningStatus winningStatus = WinningStatus.findBy(matchingCount, matchesBonusNumber);
            winningResult.update(winningStatus);
        }

        outputView.printWinningResult(winningResult);

        // 당첨 금액 / 구입 금액
        int totalPrice = winningResult.calculateTotalPrice();
        //TODO : 수익률 구하는 로직 purchasement객체로 넘길지 생각해보기.
        double earningRate = ((double) totalPrice)/purchasement.getAmount();
        outputView.printEarningRate(earningRate);
    }


}
