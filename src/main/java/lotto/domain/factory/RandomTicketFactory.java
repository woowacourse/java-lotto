package lotto.domain.factory;

import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomTicketFactory implements TicketFactory {
    @Override
    public LottoTicket create() {
        List<Integer> randomNumbers = getRandomNumbers();
        List<Integer> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < LottoTicket.LOTTO_SIZE; i++) {
            lottoNumbers.add(randomNumbers.get(i));
        }
        return new LottoTicket(lottoNumbers);
    }

    private static List<Integer> getRandomNumbers() {
        List<Integer> randomNumbers = IntStream.rangeClosed(LottoNumber.LOTTO_MIN_NUMBER, LottoNumber.LOTTO_MAX_NUMBER)
                .boxed()
                .collect(Collectors.toList());
        Collections.shuffle(randomNumbers);
        return randomNumbers;
    }
}
