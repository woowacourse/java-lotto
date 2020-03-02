package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lotto.domain.count.Count;

public class RandomNumbersFactory {

    private static List<Integer> LOTTO_NUMBERS = new ArrayList<>(LottoNumber.getLottoNumbers());
    private static final int START_INDEX = 0;
    private static final int LOTTO_SIZE = 6;

    public static List<Set<Integer>> publishAutoLottoTicketsNumbersFrom(Count count) {
        int countOfAutoLottoTickets = count.getAutoCounts();
        List<Set<Integer>> lottoTicketsNumbers = new ArrayList<>();

        for (int i = 0; i < countOfAutoLottoTickets; i++) {
            lottoTicketsNumbers.add(publishLottoTicketNumbersOfRandom());
        }

        return lottoTicketsNumbers;
    }

    private static Set<Integer> publishLottoTicketNumbersOfRandom() {
        Collections.shuffle(LOTTO_NUMBERS);
        List<Integer> slicedLottoNumbers = LOTTO_NUMBERS.subList(START_INDEX, LOTTO_SIZE);
        return new HashSet<>(slicedLottoNumbers);
    }
}

