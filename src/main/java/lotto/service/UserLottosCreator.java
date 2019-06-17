package lotto.service;

import lotto.domain.LottoCreator;
import lotto.domain.Ticket;
import lotto.domain.TicketCreator;
import lotto.domain.UserLottos;
import lotto.domain.exceptions.ExceptionMessages;
import lotto.domain.exceptions.LottoTicketException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserLottosCreator {
    private static final String DELIMETER = ",";
    private final List<Ticket> tickets;
    private final TicketCreator ticketCreator;

    public UserLottosCreator(String lottoMoney, int manualCount, List<String> manualNumbers) {
        this(lottoMoney, manualCount, manualNumbers, new LottoCreator());
    }

    public UserLottosCreator(String lottoMoney, int manualCount, List<String> manualNumbers, TicketCreator ticketCreator) {
        this.ticketCreator = ticketCreator;
        tickets = generateManual(manualCount, parseNumber(manualNumbers));
        generateAuto(parseNumber(lottoMoney));
    }

    public UserLottos create() {
        return new UserLottos(tickets);
    }

    private List<Ticket> generateManual(int manualCount, List<List<Integer>> manualNumbers) {
        validate(manualCount, manualNumbers);
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            tickets.add(ticketCreator.create(manualNumbers.get(i)));
        }
        return tickets;
    }

    private List<List<Integer>> parseNumber(List<String> manualNumbers) {
        List<List<Integer>> numbers = new ArrayList<>();
        for (String manualNumber : manualNumbers) {
            numbers.add(
                    Arrays.stream(manualNumber.split(DELIMETER))
                            .map(this::parseNumber)
                            .collect(Collectors.toList()));
        }
        return numbers;
    }

    private void generateAuto(int lottoMoney) {
        int autoTicketCount = (lottoMoney / ticketCreator.unitPrice()) - tickets.size();
        for (int i = 0; i < autoTicketCount; i++) {
            tickets.add(ticketCreator.create());
        }
    }

    private void validate(int manualCount, List<List<Integer>> manualNumbers) {
        if (manualCount != manualNumbers.size()) {
            throw new LottoTicketException(ExceptionMessages.TICKET.message());
        }
    }

    private int parseNumber(String text) {
        return Integer.parseInt(text);
    }
}
