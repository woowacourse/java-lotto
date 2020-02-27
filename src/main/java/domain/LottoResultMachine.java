package domain;

import domain.numberscontainer.Ticket;
import domain.numberscontainer.WinningNumbers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoResultMachine {
    public static Map<LottoResult, Integer> calculateResult(List<Ticket> tickets, WinningNumbers winningNumbers) {
        return tickets.stream()
                .collect(Collectors.groupingBy(winningNumbers::getLottoResult, Collectors.summingInt(x -> 1)));
    }
}