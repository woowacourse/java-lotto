package domain;

import domain.numberscontainer.Tickets;
import domain.numberscontainer.WinningNumbers;

import java.util.Map;
import java.util.stream.Collectors;

public class LottoResultMachine {
    public static Map<LottoResult, Integer> calculateResult(Tickets tickets, WinningNumbers winningNumbers) {
        return tickets.getValue()
                .stream()
                .collect(Collectors.groupingBy(winningNumbers::getLottoResult, Collectors.summingInt(x -> 1)));
    }
}