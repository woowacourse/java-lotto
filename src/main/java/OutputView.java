import java.util.List;

public class OutputView {

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

}
