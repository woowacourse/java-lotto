package domain;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    private final IntegerGenerator generator;

    public LottoMachine(IntegerGenerator generator) {
        this.generator = generator;
    }

    public LottoTicket generateLottoTicket() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < LottoTicket.LOTTO_SIZE; i++) {
            int number = extractUniqueLottoNumber(numbers);
            numbers.add(number);
        }
        return new LottoTicket(numbers.stream()
                .map(LottoNumber::new)
                .toList());
    }

    private int extractUniqueLottoNumber(List<Integer> numbers) {
        int number;
        do {
            number = generator.generateInteger(LottoNumber.LOTTO_MIN_NUMBER, LottoNumber.LOTTO_MAX_NUMBER);
        } while (numbers.contains(number));
        return number;
    }

    public LottoTickets generateLottoTickets(Payment payment) {
        List<LottoTicket> LottoTickets = new ArrayList<>();
        for (int i = 0; i < payment.getMoney() / LottoTicket.LOTTO_PRICE; i++) {
            LottoTicket LottoTicket = generateLottoTicket();
            LottoTickets.add(LottoTicket);
        }
        return new LottoTickets(LottoTickets);
    }
}
