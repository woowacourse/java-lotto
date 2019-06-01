package lotto.domain.factory;

import lotto.domain.LottoTicket;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ManualTicketFactory implements TicketFactory {
    private static final String DELIMITER = ",";

    private final String userInput;

    public ManualTicketFactory(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public LottoTicket create() {
        List<String> list = Arrays.asList(userInput.split(DELIMITER));
        return new LottoTicket(list.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
    }
}
