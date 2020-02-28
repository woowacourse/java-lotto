package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lotto.domain.count.Count;

public class LottoFactory {

    private static final List<LottoNumber> LOTTO_NUMBERS;
    private static final Map<Integer, LottoNumber> LOTTO_NUMBER_MATCHER;
    private static final int MINIMUM_LOTTO_NUMBER = LottoNumber.MINIMUM_LOTTO_NUMBER;
    private static final int MAXIMUM_LOTTO_NUMBER = LottoNumber.MAXIMUM_LOTTO_NUMBER;
    private static final int START_INDEX = 0;
    private static final int LOTTO_SIZE = 6;

    static {
        LOTTO_NUMBERS = new ArrayList<>();
        LOTTO_NUMBER_MATCHER = new HashMap<>();

        for (int i = MINIMUM_LOTTO_NUMBER; i <= MAXIMUM_LOTTO_NUMBER; i++) {
            LottoNumber lottoNumber = new LottoNumber(i);
            LOTTO_NUMBERS.add(lottoNumber);
            LOTTO_NUMBER_MATCHER.put(i, lottoNumber);
        }
    }

    public static LottoTickets publishLottoTicketsFrom(List<Set<Integer>> lottoTicketsNumbers) {
        List<LottoTicket> lottoTickets = lottoTicketsNumbers.stream()
                .map(LottoFactory::publishLottoTicketFrom)
                .collect(Collectors.toList());
        return new LottoTickets(lottoTickets);
    }

    private static LottoTicket publishLottoTicketFrom(Set<Integer> numbers) {
        Set<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoFactory::publishLottoNumberFrom)
                .collect(Collectors.toSet());
        return new LottoTicket(lottoNumbers);
    }

    public static LottoTickets publishAutoLottoTicketsFrom(Count count) {
        int countOfAutoLottoTickets = count.getAutoCounts();
        List<LottoTicket> lottoTickets = new ArrayList<>();

        for (int i = 0; i < countOfAutoLottoTickets; i++) {
            lottoTickets.add(publishLottoTicketOfRandom());
        }

        return new LottoTickets(lottoTickets);
    }

    private static LottoTicket publishLottoTicketOfRandom() {
        Collections.shuffle(LOTTO_NUMBERS);
        List<LottoNumber> slicedLottoNumbers = LOTTO_NUMBERS.subList(START_INDEX, LOTTO_SIZE);
        return new LottoTicket(new HashSet<>(slicedLottoNumbers));
    }

    public static WinningLotto publishWinningLotto(Set<Integer> lottoNumbers, Integer bonusNumber) {
        LottoTicket lottoTicket = publishLottoTicketFrom(lottoNumbers);
        LottoNumber lottoNumber = publishLottoNumberFrom(bonusNumber);
        return new WinningLotto(lottoTicket, lottoNumber);
    }

    private static LottoNumber publishLottoNumberFrom(int number) {
        return LOTTO_NUMBER_MATCHER.get(number);
    }
}

