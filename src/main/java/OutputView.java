import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class OutputView {
    public void printLottoCount(int count) {
        System.out.printf("%d개를 구매했습니다.\n", count);
    }

    public void printLottos(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            String lottoNumbers = lotto.getNumbers()
                    .stream()
                    .map(Objects::toString)
                    .collect(Collectors.joining(", ", "[", "]"));
            System.out.println(lottoNumbers);
        }
    }

    public void printWinningResult(WinningResult winningResult) {
        List<WinningInfo> sortedWinningInfo = WinningInfo.getSortedValues();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("당첨 통계\n");
        stringBuilder.append("---------\n");
        for (WinningInfo winningInfo : sortedWinningInfo) {
            stringBuilder.append(String.format("%s - %d개\n"
                    , winningInfo.getInfo()
                    , winningResult.getCount(winningInfo)));
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
}
