package lotto.domain;

import lotto.domain.exceptions.ExceptionMessages;
import lotto.domain.exceptions.LottoTicketException;
import lotto.domain.ticket.LottoCreator;
import lotto.domain.ticket.Ticket;
import lotto.domain.ticket.TicketCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserLottoSeed {
    private final List<Ticket> tickets;
    private final TicketCreator ticketCreator;

    public UserLottoSeed(String lottoMoney, int manualCount, List<String> manualNumbers) {
        this(lottoMoney, manualCount, manualNumbers, new LottoCreator());
    }

    public UserLottoSeed(String lottoMoney, int manualCount, List<String> manualNumbers, TicketCreator ticketCreator) {
        this.ticketCreator = ticketCreator;
        tickets = generateManual(manualCount, parseNumber(manualNumbers));
        generateAuto(parseNumber(lottoMoney));
    }

    private void generateAuto(int lottoMoney) {
        int autoTicketCount = (lottoMoney / ticketCreator.unitPrice()) - tickets.size();
        for (int i = 0; i < autoTicketCount; i++) {
            tickets.add(ticketCreator.create());
        }
    }

    private List<List<Integer>> parseNumber(List<String> manualNumbers) {
        List<List<Integer>> numbers = new ArrayList<>();
        for (String manualNumber : manualNumbers) {
            numbers.add(
                    Arrays.stream(manualNumber.split(","))
                            .map(this::parseNumber)
                            .collect(Collectors.toList()));
        }
        return numbers;
    }

    private List<Ticket> generateManual(int manualCount, List<List<Integer>> manualNumbers) {
        validate(manualCount, manualNumbers);
        List<Ticket> tickets = new ArrayList<>();
        for (int i = 0; i < manualCount; i++) {
            tickets.add(ticketCreator.create(manualNumbers.get(i)));
        }
        return tickets;
    }

    private void validate(int manualCount, List<List<Integer>> manualNumbers) {
        if (manualCount != manualNumbers.size()) {
            throw new LottoTicketException(ExceptionMessages.TICKET.message());
        }
    }

    private int parseNumber(String text) {
        return Integer.parseInt(text);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}
