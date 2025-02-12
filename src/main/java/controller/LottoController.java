package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.BonusNumber;
import model.Lotto;
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
        int purchaseAmount = inputView.readPurchaseAmount();
        int count = purchaseAmount / 1000;
        System.out.println(count + "개를 구매했습니다.");

        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(new Lotto());
        }

        for(Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }

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

        for (WinningStatus winningStatus : winningResult.getWinningResults().keySet()) {
            if(winningStatus == WinningStatus.NONE) continue;
            int winningCount = winningResult.getWinningResults().get(winningStatus);
            System.out.printf("%s - %d개\n", winningStatus.getExpression(), winningCount);
        }

        // 당첨 금액 / 구입 금액
        int totalPrice = winningResult.calculateTotalPrice();
        double earningRate = ((double) totalPrice)/purchaseAmount;
        System.out.printf("총 수익률은 %.2f입니다.", earningRate);
    }


}
