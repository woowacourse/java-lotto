package controller;

import domain.*;
import enums.LottoRanking;
import view.InputView;
import view.OutputView;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoController {

    public void start() throws IOException {
        Price price = InputView.getPrice();

        LottoMachine lottoMachine = buyLottos(price);
        Lottos lottos = printLottos(lottoMachine, price);

        LottoResult lottoResult = calculateLottoResult(lottos);

        printLottoResults(lottoResult);
        printRateOfReturn(lottoResult, price);
    }

    private LottoMachine buyLottos(Price price) {
        LottoMachine lottoMachine = new LottoMachine();
        int ticketCount = price.getTicketCount();

        OutputView.printTicketsContMessage(ticketCount);

        return lottoMachine;
    }

    private Lottos printLottos(LottoMachine lottoMachine, Price price) {
        Lottos lottos = lottoMachine.generateLottos(price.getTicketCount());

        OutputView.printLottos(lottos);

        return lottos;
    }

    private LottoResult calculateLottoResult(Lottos lottos) throws IOException {
        WinningNumberWithBonusNumber winningNumberWithBonusNumber
                = new WinningNumberWithBonusNumber(InputView.getWinningNumber(), InputView.getBonusNumber());

        LottoResult lottoResult = LottoWinningChecker.calculateResult(lottos, winningNumberWithBonusNumber);
        return lottoResult;
    }

    private void printLottoResults(LottoResult lottoResult) {

        OutputView.printBanner();

        Map<LottoRanking, Integer> result = lottoResult.result();
        result.remove(LottoRanking.LOSING);
        List<LottoRanking> lottoRankings = result.keySet().stream().collect(Collectors.toList());
        Collections.reverse(lottoRankings);

        OutputView.printLottoResult(lottoRankings, result);
    }

    private void printRateOfReturn(LottoResult lottoResult, Price price) {
        double rateOfReturn = lottoResult.getLottoRate(price.getValue());

        OutputView.printRateOfReturn(rateOfReturn);
    }
}
