package view;

import domain.Lotto;
import domain.Lottos;
import enums.LottoRanking;

import java.util.List;
import java.util.Map;

public class OutputView {
    private OutputView() {
    }

    public static void printTicketsContMessage(int ticket) {
        System.out.println(ticket + "개를 구매했습니다.");
    }

    public static void printLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            String formattedNumbers = "[" + String.join(", ",
                    lotto.getNumbers().stream().map(String::valueOf).toList()) + "]";
            System.out.println(formattedNumbers);
        }
        System.out.println();
    }

    public static void printRateOfReturn(double rateOfReturn) {
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

    public static void printBanner() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append("\n")
                .append("당첨 통계")
                .append("\n")
                .append("---------");
        System.out.println(stringBuilder.toString());
    }

    public static void printLottoResult(List<LottoRanking> lottoRankings,Map<LottoRanking, Integer> result) {
        StringBuilder stringBuilder = new StringBuilder();

        for (LottoRanking lottoRanking : lottoRankings) {
            printCorrectCount(lottoRanking, stringBuilder);
            stringBuilder = printForSecondRank(lottoRanking, stringBuilder);
            printStatistics(lottoRanking, stringBuilder, result);
        }

        System.out.println(stringBuilder.toString().trim());
    }

    private static void printCorrectCount(LottoRanking lottoRanking, StringBuilder stringBuilder) {
        stringBuilder
                .append(lottoRanking.getCorrectCount())
                .append("개 일치 ");
    }

    private static StringBuilder printForSecondRank(LottoRanking lottoRanking, StringBuilder stringBuilder) {
        if (lottoRanking == LottoRanking.SECOND) {
            stringBuilder = new StringBuilder(stringBuilder.toString().trim());
            stringBuilder.append(", 보너스 볼 일치");
        }
        return stringBuilder;
    }

    private static void printStatistics(LottoRanking lottoRanking, StringBuilder stringBuilder, Map<LottoRanking, Integer> result) {
        stringBuilder
                .append("(")
                .append(lottoRanking.getPrize() + "원)- ")
                .append(result.get(lottoRanking) + "개")
                .append("\n");
    }
}
