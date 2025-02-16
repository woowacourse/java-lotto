package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    public LottoTicket generateLottoTicket(IntegerGenerator generator) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < LottoTicket.LOTTO_SIZE; i++) {
            int number = extractUniqueLottoNumber(generator, numbers);
            numbers.add(number);
        }
        return new LottoTicket(numbers.stream()
                .map(LottoNumber::new)
                .toList());
    }

    private int extractUniqueLottoNumber(IntegerGenerator generator, List<Integer> numbers) {
        int number;
        do {
            number = generator.generateInteger(LottoNumber.LOTTO_MIN_NUMBER, LottoNumber.LOTTO_MAX_NUMBER);
        } while (numbers.contains(number));
        return number;
    }

    public LottoTickets generateLottoTickets(Payment payment, IntegerGenerator generator) {
        List<LottoTicket> LottoTickets = new ArrayList<>();
        for (int i = 0; i < payment.getMoney() / LottoTicket.LOTTO_PRICE; i++) {
            LottoTicket LottoTicket = generateLottoTicket(generator);
            LottoTickets.add(LottoTicket);
        }
        return new LottoTickets(LottoTickets);
    }
}
