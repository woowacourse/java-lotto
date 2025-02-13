package domain;

import static domain.LottoTicket.LOTTO_MAX_NUMBER;
import static domain.LottoTicket.LOTTO_MIN_NUMBER;
import static domain.LottoTicket.LOTTO_SIZE;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    public LottoTicket generateLottoTicket(IntegerGenerator generator) {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < LOTTO_SIZE; i++) {
            int number = extractUniqueLottoNumber(generator, lottoNumbers);
            lottoNumbers.add(number);
        }
        return new LottoTicket(lottoNumbers);
    }

    private static int extractUniqueLottoNumber(IntegerGenerator generator, List<Integer> lottoNumbers) {
        int number = -1;
        do {
            number = generator.generateIntegerInRange(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER);
        } while (lottoNumbers.contains(number));
        return number;
    }

    public List<LottoTicket> generateLottoTickets(int lottoTicketNumber, IntegerGenerator generator) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        for (int i = 0; i < lottoTicketNumber; i++) {
            LottoTicket lottoTicket = generateLottoTicket(generator);
            lottoTickets.add(lottoTicket);
        }
        return lottoTickets;
    }
}
