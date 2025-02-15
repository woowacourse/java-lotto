package domain;

import static domain.LottoTicket.LOTTO_PRICE;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    public LottoTicket generateLottoTicket(IntegerGenerator generator) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < LottoNumbers.LOTTO_SIZE; i++) {
            int number = extractUniqueLottoNumber(generator, numbers);
            numbers.add(number);
        }
        return new LottoTicket(new LottoNumbers(numbers));
    }

    private int extractUniqueLottoNumber(IntegerGenerator generator, List<Integer> lottoNumbers) {
        int number;
        do {
            number = generator.generateInteger(LottoNumbers.LOTTO_MIN_NUMBER, LottoNumbers.LOTTO_MAX_NUMBER);
        } while (lottoNumbers.contains(number));
        return number;
    }

    public LottoTickets generateLottoTickets(int purchaseAmount, IntegerGenerator generator) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < purchaseAmount / LOTTO_PRICE; i++) {
            LottoTicket lottoTicket = generateLottoTicket(generator);
            lottoTickets.add(lottoTicket);
        }
        return new LottoTickets(lottoTickets);
    }
}
