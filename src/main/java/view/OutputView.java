package view;

import domain.Lotto;
import domain.WinningInfo;
import domain.WinningResult;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OutputView {
    public void printLottoCount(int count) {
        System.out.printf("%d개를 구매했습니다.\n", count);
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(formatLottoMessage(lotto));
        }
    }

    private String formatLottoMessage(Lotto lotto) {
        return lotto.getNumbers()
                .stream()
                .map(Objects::toString)
                .collect(Collectors.joining(", ", "[", "]"));
    }

    public void printWinningResult(WinningResult winningResult) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("당첨 통계\n");
        stringBuilder.append("---------\n");
        for (WinningInfo winningInfo : WinningInfo.getSortedValues()) {
            stringBuilder.append(formatWinningInfoMessage(winningInfo));
            stringBuilder.append(String.format(" - %d개\n", winningResult.getCount(winningInfo)));
        }
        System.out.println(stringBuilder);
    }

    private String formatWinningInfoMessage(WinningInfo winningInfo) {
        if (winningInfo == WinningInfo.SECOND_PRIZE) {
            return String.format("%d개 일치, 보너스 볼 일치 (%d원)", winningInfo.getMatchedNumberCount(), winningInfo.getPrice());
        }
        return String.format("%d개 일치 (%d원)", winningInfo.getMatchedNumberCount(), winningInfo.getPrice());
    }

    public void printRevenue(float revenue) {
        StringBuilder stringBuilder = new StringBuilder(String.format("총 수익률은 %.2f입니다.", revenue));
        if (revenue < 1) {
            stringBuilder.append("(기준이 1이기 때문에 결과적으로 손해라는 의미임)");
        }
        stringBuilder.append('\n');
        System.out.println(stringBuilder);
    }
}
