package domain;

import static domain.LottoTicket.LOTTO_MAX_NUMBER;
import static domain.LottoTicket.LOTTO_MIN_NUMBER;
import static domain.LottoTicket.LOTTO_PRICE;
import static domain.LottoTicket.LOTTO_SIZE;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    public LottoTicket generateLottoTicket(IntegerGenerator generator) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < LOTTO_SIZE; i++) {
            int number = extractUniqueLottoNumber(generator, numbers);
            numbers.add(number);
        }
        return new LottoTicket(numbers);
    }

    private int extractUniqueLottoNumber(IntegerGenerator generator, List<Integer> lottoNumbers) {
        int number;
        do {
            number = generator.generateInteger(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER);
        } while (lottoNumbers.contains(number));
        return number;
    }

    public List<LottoTicket> generateLottoTickets(int purchaseAmount, IntegerGenerator generator) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < purchaseAmount / LOTTO_PRICE; i++) {
            LottoTicket lottoTicket = generateLottoTicket(generator);
            lottoTickets.add(lottoTicket);
        }
        return lottoTickets;
    }
}
