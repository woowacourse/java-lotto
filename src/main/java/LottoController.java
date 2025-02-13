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
        System.out.println("구입금액을 입력해 주세요.");
        Price price = new Price(bufferedReader.readLine());
        LottoMachine lottoMachine = new LottoMachine(price);

        int ticket = lottoMachine.getTicket();
        System.out.println(ticket + "개를 구매했습니다.");

        Lottos lottos = lottoMachine.generateLottos();
        for (Lotto lotto : lottos.getLottos()) {
            System.out.println(lotto.toString());
        }

        WinningNumberWithBonusNumber winningNumberWithBonusNumber
                = new WinningNumberWithBonusNumber(getWinningNumber(bufferedReader), getBonusNumber(bufferedReader));

        LottoResult lottoResult = LottoWinningChecker.calculateResult(lottos, winningNumberWithBonusNumber);

        printLottoResults(lottoResult);

        printRateOfReturn(lottoResult,price);

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
        stringBuilder.append("당첨 통계\n" +
                "---------\n");
        Map<LottoRanking, Integer> result = lottoResult.result();
        result.remove(LottoRanking.LOSING);

        List<LottoRanking> lottoRankings = result.keySet().stream().collect(Collectors.toList());
        Collections.reverse(lottoRankings);

        for (LottoRanking lottoRanking : lottoRankings) {
            if (lottoRanking == LottoRanking.SECOND) {
                stringBuilder.append(lottoRanking.getCorrectCount() + "개 일치, 보너스 볼 일치(" + lottoRanking.getPrize() + "원)- " + result.get(lottoRanking) + "개\n");
                continue;
            }
            stringBuilder.append(lottoRanking.getCorrectCount() + "개 일치 (" + lottoRanking.getPrize() + "원)- " + result.get(lottoRanking) + "개\n");
        }
        System.out.println(stringBuilder);
    }

    private void printRateOfReturn(LottoResult lottoResult, Price price) {
        double rateOfReturn = (double) lottoResult.getTotalPrize() / price.getValue();
        System.out.print("총 수익률은 " + lottoResult.getTotalPrize() / price.getValue() + "입니다.");
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
