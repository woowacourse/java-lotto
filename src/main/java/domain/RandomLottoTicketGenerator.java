package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomLottoTicketGenerator implements LottoTicketGenerator {
    public LottoTicket generateLottoTicket(int startInclusive, int endInclusive) {
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < LottoTicket.LOTTO_SIZE; i++) {
            int number = extractUniqueLottoNumber(startInclusive, endInclusive, lottoNumbers);
            lottoNumbers.add(number);
        }
        return new LottoTicket(lottoNumbers);
    }

    private int extractUniqueLottoNumber(int startInclusive, int endInclusive, List<Integer> lottoNumbers) {
        int number = -1;
        Random random = ThreadLocalRandom.current();
        do {
            number = random.nextInt(startInclusive, endInclusive + 1);
        } while (lottoNumbers.contains(number));
        return number;
    }
}
