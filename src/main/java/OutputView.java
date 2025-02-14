import java.util.List;

public class OutputView {
    public void printLottoCount(int count) {
        System.out.printf("%d개를 구매했습니다.\n", count);
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getInfo());
        }
    }

    public void printWinningResult(WinningResult winningResult) {
        List<LottoPrize> sortedLottoPrize = LottoPrize.getSortedValues();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("당첨 통계\n");
        stringBuilder.append("---------\n");
        for (LottoPrize lottoPrize : sortedLottoPrize) {
            stringBuilder.append(
                    String.format("%s - %d개\n", getFormattedPrize(lottoPrize), winningResult.getCount(lottoPrize))
            );
        }
        System.out.println(stringBuilder);
    }

    public void printRevenue(float revenue) {
        StringBuilder stringBuilder = new StringBuilder(String.format("총 수익률은 %.2f입니다.", revenue));
        if (revenue < 1) {
            stringBuilder.append("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
        stringBuilder.append('\n');
        System.out.println(stringBuilder);
    }

    private String getFormattedPrize(LottoPrize lottoPrize) {
        if (lottoPrize == LottoPrize.SECOND) {
            return String.format(
                    "%d개 일치, 보너스 볼 일치 (%d원)",
                    lottoPrize.getMatchedNumberCount(),
                    lottoPrize.getPrice()
            );
        }
        return String.format("%d개 일치 (%d원)", lottoPrize.getMatchedNumberCount(), lottoPrize.getPrice());
    }
}
