package controller;

import domain.Lotto;
import domain.LottoRanking;
import domain.LottoResult;
import domain.Lottos;
import domain.Money;
import domain.WinningNumberWithBonusNumber;
import service.LottoMachine;
import service.LottoWinningChecker;
import view.InputHandler;
import view.OutputHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoController {

    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;
    private final LottoMachine lottoMachine;
    private final LottoWinningChecker lottoWinningChecker;

    public LottoController(InputHandler inputHandler,
                           OutputHandler outputHandler,
                           LottoMachine lottoMachine,
                           LottoWinningChecker lottoWinningChecker) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
        this.lottoMachine = lottoMachine;
        this.lottoWinningChecker = lottoWinningChecker;
    }

    public void start() throws IOException {
        Money money = getMoney();

        Lottos lottos = lottoMachine.buyLottos(money);
        printLottos(lottos);

        WinningNumberWithBonusNumber winningNumberWithBonusNumber = getWinningNumberWithBonusNumber();

        LottoResult lottoResult = lottoWinningChecker.calculateResult(lottos, winningNumberWithBonusNumber);

        printLottoResults(lottoResult);
        printRateOfReturn(lottoResult, money);
    }

    private Money getMoney() throws IOException {
        String money = inputHandler.readMoney();
        return new Money(money);
    }

    private void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.toString());
        }
        System.out.println();
    }

    private WinningNumberWithBonusNumber getWinningNumberWithBonusNumber() throws IOException {
        return new WinningNumberWithBonusNumber(
                getWinningNumber(),
                getBonusNumber());
    }

    private Lotto getWinningNumber() throws IOException {
        String winningNumber = inputHandler.readWinningNumber();

        return new Lotto(Arrays.stream(winningNumber.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
    }

    private int getBonusNumber() throws IOException {
        String bonusNumber = inputHandler.readBonusNumber();

        return Integer.parseInt(bonusNumber);
    }

    private void printLottoResults(LottoResult lottoResult) {
        StringBuilder stringBuilder = new StringBuilder();
        printBanner(stringBuilder);

        Map<LottoRanking, Integer> result = lottoResult.result();
        result.remove(LottoRanking.LOSING);
        List<LottoRanking> lottoRankings = result.keySet().stream().collect(Collectors.toList());
        Collections.reverse(lottoRankings);

        for (LottoRanking lottoRanking : lottoRankings) {
            printCorrectCount(lottoRanking, stringBuilder);
            stringBuilder = printForSecondRank(lottoRanking, stringBuilder);
            printStatistics(lottoRanking, stringBuilder, result);
        }

        System.out.println(stringBuilder.toString().trim());
    }

    private void printCorrectCount(LottoRanking lottoRanking, StringBuilder stringBuilder) {
        stringBuilder
                .append(lottoRanking.getCorrectCount())
                .append("개 일치 ");
    }

    private void printStatistics(LottoRanking lottoRanking, StringBuilder stringBuilder, Map<LottoRanking, Integer> result) {
        stringBuilder
                .append("(")
                .append(lottoRanking.getPrize() + "원)- ")
                .append(result.get(lottoRanking) + "개")
                .append("\n");
    }

    private StringBuilder printForSecondRank(LottoRanking lottoRanking, StringBuilder stringBuilder) {
        if (lottoRanking == LottoRanking.SECOND) {
            stringBuilder = new StringBuilder(stringBuilder.toString().trim());
            stringBuilder.append(", 보너스 볼 일치");
        }
        return stringBuilder;
    }

    private void printBanner(StringBuilder stringBuilder) {
        stringBuilder
                .append("\n")
                .append("당첨 통계")
                .append("\n")
                .append("---------")
                .append("\n");
    }

    private void printRateOfReturn(LottoResult lottoResult, Money money) {
        double rateOfReturn = (double) lottoResult.getTotalPrize() / money.getAmount();
        System.out.printf("총 수익률은 %.2f 입니다.", (int) (rateOfReturn * 100) / 100.0);
        if (rateOfReturn > 1) {
            System.out.print("(기준이 1이기 때문에 결과적으로 이득이라는 의미임)");
            return;
        }
        if (rateOfReturn == 1) {
            System.out.print("(기준이 1이기 때문에 결과적으로 동등이라는 의미임)");
            return;
        }
        if (rateOfReturn < 1) {
            System.out.print("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
    }
}
