package lotto.dto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TicketNumbersAssembler {

    private TicketNumbersAssembler() {
    }

    public static TicketNumbersDto writeTicketNumbersDto(String[] inputTicketNumbers) {
        List<Integer> ticketNumbers = Arrays.stream(inputTicketNumbers)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return new TicketNumbersDto(ticketNumbers);
    }
}
