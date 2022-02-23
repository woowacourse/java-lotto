package lotto.controller;

import lotto.domain.*;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class LottoController {

    public void run() {

        // 구입금액 입력 , 티켓 구매
        PurchaseAmount purchaseAmount = new PurchaseAmount(InputView.getAmount());
        int ticketCount = purchaseAmount.calcTheNumberOfTicket();

        OutputView.printTicketCount(ticketCount);
        LottoMachine lottoMachine = new LottoMachine();
        List<Set<LottoNumber>> lottoTickets = lottoMachine.makeLottoTickets(ticketCount);
        OutputView.printTickets(lottoTickets);

        // 당첨 번호, 보너스 번호 생성
        WinningNumber winningNumber = new WinningNumber(InputView.getWinningNumber());
        LottoNumber bonusNumber = new LottoNumber(InputView.getBonusNumber());

        TotalNumber totalNumber = new TotalNumber(winningNumber, bonusNumber);

        // 결과 계산
        RankCalculator rankCalculator = new RankCalculator();
        Map<Rank, Integer> rank = rankCalculator.calcRank(totalNumber, lottoTickets);
        double profitRatio = rankCalculator.calcProfitRatio(purchaseAmount.getAmount());

        OutputView.printResult(rank, profitRatio);
    }
}
