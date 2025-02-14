package lotto.view.output;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ConsoleOutputView implements OutputView {

    private final Comparator<LottoPrizeResponse> lottoPrizeResponseComparator =
            Comparator.comparingInt(LottoPrizeResponse::getMatchCount);

    @Override
    public void printInputPurchaseMoneyMessage() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    @Override
    public void printPurchasedLottos(List<LottoResponse> responses) {
        System.out.printf("%d개를 구매했습니다.\n", responses.size());
        responses.forEach(this::printLotto);
    }

    @Override
    public void printInputWinningLottoNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");
    }

    @Override
    public void printInputBonusNumber() {
        System.out.println("보너스 볼을 입력해 주세요.");
    }

    @Override
    public void printLottoResults(List<LottoPrizeResponse> lottoPrizeResponses, int purchaseMoney) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        Map<LottoPrizeResponse, Integer> lottoPrizeStatistic = getLottoPrizeStatistic(lottoPrizeResponses);
        lottoPrizeStatistic.forEach(this::printLottoResult);
        printProfitRate(getProfitRate(lottoPrizeStatistic, purchaseMoney));
    }

    private void printLottoResult(LottoPrizeResponse lottoPrizeResponse, int amount) {
        int matchCount = lottoPrizeResponse.getMatchCount();
        int prize = lottoPrizeResponse.getPrize();
        boolean isBonusNumberMatches = lottoPrizeResponse.isBonusNumberMatches();

        StringBuilder message = new StringBuilder(matchCount + "개 일치");

        if (isBonusNumberMatches) {
            message.append(", 보너스 볼 일치");
        }
        message.append(" (").append(prize).append("원)").append(" - ").append(amount).append("개");

        System.out.println(message);
    }

    private Map<LottoPrizeResponse, Integer> getLottoPrizeStatistic(List<LottoPrizeResponse> lottoPrizeResponses) {

        Map<LottoPrizeResponse, Integer> statistic = new TreeMap<>(lottoPrizeResponseComparator);

        lottoPrizeResponses.stream()
                .filter(LottoPrizeResponse::isWin)
                .forEach(lottoPrizeResponse -> {
                    statistic.put(lottoPrizeResponse,
                            statistic.getOrDefault(lottoPrizeResponse, 0) + 1
                    );
                });

        return statistic;
    }

    private void printLotto(LottoResponse response) {
        System.out.println(response.getNumbers().stream().sorted().toList());
    }

    private double getProfitRate(Map<LottoPrizeResponse, Integer> lottoPrizeResponseStatistic, int purchaseMoney) {

        return Math.round(lottoPrizeResponseStatistic.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum() / purchaseMoney * 100) / 100.0;
    }

    private void printProfitRate(double profitRate) {
        System.out.print("총 수익률은 " + profitRate + "입니다.");

        if (profitRate < 1) {
            System.out.println("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }

        System.out.println();
    }
}
