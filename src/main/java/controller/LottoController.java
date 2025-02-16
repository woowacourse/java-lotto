package controller;

import enums.LottoRanking;
import model.*;
import view.OutputView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoController {

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        
        Price price = getPrice(bufferedReader);

        LottoMachine lottoMachine = buyLottos(price);
        Lottos lottos = printLottos(lottoMachine);

        LottoResult lottoResult = calculateLottoResult(bufferedReader, lottos);

        printLottoResults(lottoResult);
        printRateOfReturn(lottoResult, price);
    }

    private Price getPrice(BufferedReader bufferedReader) throws IOException {
        System.out.println("구입금액을 입력해 주세요.");
        return new Price(bufferedReader.readLine());
    }

    private LottoMachine buyLottos(Price price) {
        LottoMachine lottoMachine = new LottoMachine(price);
        int ticket = lottoMachine.getTicket();

        OutputView.printTicketsContMessage(ticket);

        return lottoMachine;
    }

    private Lottos printLottos(LottoMachine lottoMachine) {
        Lottos lottos = lottoMachine.generateLottos();

        OutputView.printLottos(lottos);

        return lottos;
    }

    private LottoResult calculateLottoResult(BufferedReader bufferedReader, Lottos lottos) throws IOException {
        WinningNumberWithBonusNumber winningNumberWithBonusNumber
                = new WinningNumberWithBonusNumber(getWinningNumber(bufferedReader), getBonusNumber(bufferedReader));

        LottoResult lottoResult = LottoWinningChecker.calculateResult(lottos, winningNumberWithBonusNumber);
        return lottoResult;
    }

    private Lotto getWinningNumber(BufferedReader bufferedReader) throws IOException {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
        Numbers numbers = new Numbers(Arrays.stream(bufferedReader.readLine().split(",")).map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
        return new Lotto(numbers);
    }

    private int getBonusNumber(BufferedReader bufferedReader) throws IOException {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(bufferedReader.readLine());
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
