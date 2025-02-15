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

    public void printLottoCount(int count) {
        System.out.printf("%d개를 구매했습니다.%n", count);
    }

    public void printLottosInfo(Lottos lottos) {
        System.out.println(
                String.join("\n", lottos.getLottos().stream()
                        .map(Lotto::toString)
                        .toList()));

        System.out.println();
    }

    public void printLottoResults(LottoResult lottoResult) {
        printBanner();

        StringBuilder lottoResultMessage = new StringBuilder();

        Map<LottoRanking, Integer> result = lottoResult.result();
        result.remove(LottoRanking.LOSING);

        List<LottoRanking> lottoRankings = result.keySet().stream()
                .sorted(Collections.reverseOrder())
                .toList();

        for (LottoRanking lottoRanking : lottoRankings) {
            lottoResultMessage
                    .append(lottoRanking.getCorrectCount())
                    .append("개 일치")
                    .append(buildLottoRanking(lottoRanking, result));
        }

        System.out.println(lottoResultMessage.toString().trim());
    }

    public void printRateOfReturn(LottoResult lottoResult, Money money) {
        double rateOfReturn = (double) lottoResult.getTotalPrize() / money.getAmount();
        double formattedRate = (int) (rateOfReturn * 100) / 100.0;

        System.out.printf("총 수익률은 %.2f 입니다.", formattedRate);

        printRateMessage(rateOfReturn);
    }

    private void printBanner() {
        System.out.printf(String.format("%n당첨 통계%n--------- %n"));
    }

    private String buildLottoRanking(LottoRanking lottoRanking, Map<LottoRanking, Integer> result) {
        StringBuilder lottoRankingMessage = new StringBuilder();

        appendBonusBallMessage(lottoRanking, lottoRankingMessage);

        lottoRankingMessage
                .append(String.format("(%d원)- ", lottoRanking.getPrize()))
                .append(String.format("%d개", result.get(lottoRanking)));

        lottoRankingMessage.append("\n");
        return lottoRankingMessage.toString();
    }

    private void appendBonusBallMessage(LottoRanking lottoRanking, StringBuilder lottoRankingMessage) {
        if (lottoRanking == LottoRanking.SECOND) {
            lottoRankingMessage.append(", 보너스 볼 일치");
            return;
        }
        lottoRankingMessage.append(" ");
    }

    private static void printRateMessage(double rateOfReturn) {
        String resultType = getResultType(rateOfReturn);

        System.out.printf("(기준이 1이기 때문에 결과적으로 %s라는 의미임)%n", resultType);
    }

    private static String getResultType(double rateOfReturn) {
        if (rateOfReturn < 1) {
            return "손해";
        }
        if (rateOfReturn > 1) {
            return "이득이";
        }
        return "동등이";
    }
}
