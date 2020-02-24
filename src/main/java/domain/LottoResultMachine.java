package domain;

import domain.numberscontainer.Ticket;
import domain.numberscontainer.WinningNumbers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LottoResultMachine {

    public static Map<LottoResult, Integer> confirmResult(List<Ticket> tickets, WinningNumbers winningNumbers) {
        return tickets.stream()
                .collect(Collectors.groupingBy(winningNumbers::getLottoResult, countingLottoResult()));
    }

    private static Collector<Ticket, ?, Integer> countingLottoResult() {
        return Collectors.reducing(0, e -> 1, Integer::sum);
    }
}