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
        System.out.println(ticket + "개를 구매했습니다.");
        return lottoMachine;
    }

    private Lottos printLottos(LottoMachine lottoMachine) {
        Lottos lottos = lottoMachine.generateLottos();
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.toString());
        }
        System.out.println();
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
        return new Lotto(Arrays.stream(bufferedReader.readLine().split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
    }

    private int getBonusNumber(BufferedReader bufferedReader) throws IOException {
        System.out.println("보너스 볼을 입력해 주세요.");
        return Integer.parseInt(bufferedReader.readLine());
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

    private void printRateOfReturn(LottoResult lottoResult, Price price) {
        double rateOfReturn = (double) lottoResult.getTotalPrize() / price.getValue();
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
