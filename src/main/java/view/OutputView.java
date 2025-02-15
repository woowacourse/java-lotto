package view;

import domain.WinningCounter;
import java.util.List;
import java.util.stream.IntStream;

public class OutputView {

    public void printLottoQuantity(int quantity) {
        System.out.println(quantity + "개를 구매했습니다.");
    }

    public void printLotto(List<Integer> ballNumbers) {
        System.out.print("[");
        IntStream.range(0, ballNumbers.size() - 1)
                .forEach(i -> System.out.print(ballNumbers.get(i) + ", "));
        System.out.println(ballNumbers.getLast() + "]");
    }

    public void printWinningStatistics(List<WinningCounter> winningCounters) {
        winningCounters.stream()
                .map(this::printWinningInfo)
                .forEach(System.out::println);
    }

    private String printWinningInfo(WinningCounter winningCounter) {
        return String.format("%d개 일치 (%d원)- %d개",
                winningCounter.getWinningStatistics().getMatchCount(),
                winningCounter.getWinningStatistics().getPrizeMoney(),
                winningCounter.getCount());
    }

    public void printYield(double yield) {
        System.out.println(String.format("총 수익률은 %.2f입니다.", yield));
    }

}
