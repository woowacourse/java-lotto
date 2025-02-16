package domain;

import static domain.LottoTicket.LOTTO_MAX_NUMBER;
import static domain.LottoTicket.LOTTO_MIN_NUMBER;
import static domain.LottoTicket.LOTTO_PRICE;
import static domain.LottoTicket.LOTTO_SIZE;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    private List<Integer> generateLottoTicket(IntegerGenerator generator) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < LOTTO_SIZE; i++) {
            int number = extractUniqueNumber(generator, numbers);
            numbers.add(number);
        }
        return numbers;
    }

    private int extractUniqueNumber(IntegerGenerator generator, List<Integer> numbers) {
        int number = -1;
        do {
            number = generator.generateInteger(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER);
        } while (numbers.contains(number));
        return number;
    }

    public List<LottoTicket> generateLottoTickets(int purchaseAmount, IntegerGenerator generator) {
        int lottoTicketNumber = purchaseAmount / LOTTO_PRICE;
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < lottoTicketNumber; i++) {
            List<Integer> numbers = generateLottoTicket(generator);
            lottoTickets.add(LottoTicket.from(numbers));
        }
        return lottoTickets;
    }
}
