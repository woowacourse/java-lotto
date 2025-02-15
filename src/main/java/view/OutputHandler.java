package view;

import domain.Lotto;
import domain.LottoRanking;
import domain.LottoResult;
import domain.Lottos;
import domain.Money;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class OutputHandler {

    public void printLottos(Lottos lottos) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append(lottos.getLottoCount())
                .append("개를 구매했습니다.")
                .append("\n");

        for (Lotto lotto : lottos.getLottos()) {
            stringBuilder
                    .append(lotto.toString())
                    .append("\n");
        }

        System.out.println(stringBuilder);
    }

    public void printLottoResults(LottoResult lottoResult) {
        StringBuilder stringBuilder = new StringBuilder();
        printBanner(stringBuilder);

        Map<LottoRanking, Integer> result = lottoResult.result();
        result.remove(LottoRanking.LOSING);

        List<LottoRanking> lottoRankings = result.keySet().stream()
                .sorted(Collections.reverseOrder())
                .toList();

        for (LottoRanking lottoRanking : lottoRankings) {
            printCorrectCount(lottoRanking);
            stringBuilder.append(formatLottoRanking(lottoRanking, result));
        }

        System.out.println(stringBuilder.toString().trim());
    }

    private void printBanner(StringBuilder stringBuilder) {
        stringBuilder.append("\n")
                .append("당첨 통계")
                .append("\n")
                .append("---------")
                .append("\n");
    }

    private void printCorrectCount(LottoRanking lottoRanking) {
        System.out.println(lottoRanking.getCorrectCount() + "개 일치 ");
    }

    private String formatLottoRanking(LottoRanking lottoRanking, Map<LottoRanking, Integer> result) {
        StringBuilder sb = new StringBuilder();
        sb.append("(")
                .append(lottoRanking.getPrize())
                .append("원) - ")
                .append(result.getOrDefault(lottoRanking, 0))
                .append("개");

        if (lottoRanking == LottoRanking.SECOND) {
            sb.append(", 보너스 볼 일치");
        }

        sb.append("\n");
        return sb.toString();
    }

    public void printRateOfReturn(LottoResult lottoResult, Money money) {
        double rateOfReturn = (double) lottoResult.getTotalPrize() / money.getAmount();
        System.out.printf("총 수익률은 %.2f 입니다.", (int) (rateOfReturn * 100) / 100.0);

        if (rateOfReturn > 1) {
            System.out.println("(기준이 1이기 때문에 결과적으로 이득이라는 의미임)");
        } else if (rateOfReturn == 1) {
            System.out.println("(기준이 1이기 때문에 결과적으로 동등이라는 의미임)");
        } else {
            System.out.println("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
    }
}