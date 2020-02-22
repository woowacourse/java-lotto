package domain;

import domain.lottonumber.LottoTicket;
import util.LottoNumbersDtoGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoStore {
    public static List<LottoTicket> generateTickets(int number) {
        List<LottoTicket> lottoTickets = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            lottoTickets.add(new LottoTicket(LottoNumbersDtoGenerator.generateRandomTicketDto()));
        }

        return lottoTickets;
    }

    public static List<LottoTicket> generateTickets(int number, List<Set<Integer>> myNumbers) {
        List<LottoTicket> manualTickets = myNumbers.stream()
                .map(numbers -> LottoNumbersDtoGenerator.generateManualNumbersDto(numbers, -1))
                .map(LottoTicket::new)
                .collect(Collectors.toList());

        int randomTicketsNumber = getRandomTicketsNumber(number, myNumbers);
        List<LottoTicket> randomTickets = generateTickets(randomTicketsNumber);
        manualTickets.addAll(randomTickets);

        return manualTickets;
    }

    private static int getRandomTicketsNumber(int number, List<Set<Integer>> myNumbers) {
        return number - myNumbers.size();
    }
}
