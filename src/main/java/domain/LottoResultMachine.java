package domain;

import domain.numberscontainer.Ticket;
import domain.numberscontainer.Tickets;
import domain.numberscontainer.WinningNumbers;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LottoResultMachine {
    public static LottoResult calculateResult(Tickets tickets, WinningNumbers winningNumbers) {
        Map<LottoRank, Integer> lottoResult = tickets.getValue()
                .stream()
                .collect(Collectors.groupingBy(winningNumbers::getLottoRank, Collectors.summingInt(x -> 1)));
        return new LottoResult(lottoResult);
    }
}