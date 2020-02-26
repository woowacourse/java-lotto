package lotto.domain.lotto;

import lotto.domain.count.Count;
import lotto.dto.request.WinningLottoDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoTicketFactory {

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

    public static LottoTickets publishLottoTickets(Count count) {
        List<LottoTicket> lottoTickets = new ArrayList<>();
        int lottoPurchaseCounts = count.getAutoCounts();

        for (int i = START_INDEX; i < lottoPurchaseCounts; i++) {
            lottoTickets.add(publishLottoTicketOfRandom());
        }

        return new LottoTickets(lottoTickets);
    }

    private static LottoTicket publishLottoTicketOfRandom() {
        Collections.shuffle(LOTTO_NUMBERS);
        List<LottoNumber> slicedLottoNumbers = LOTTO_NUMBERS.subList(START_INDEX, LOTTO_SIZE);
        return new LottoTicket(new HashSet<>(slicedLottoNumbers));
    }

    public static LottoTicket publishLottoTicketFrom(Set<Integer> numbers) {
        Set<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoTicketFactory::publishLottoNumberFrom)
                .collect(Collectors.toSet());
        return new LottoTicket(lottoNumbers);
    }

    public static LottoNumber publishLottoNumberFrom(int number) {
        return LOTTO_NUMBER_MATCHER.get(number);
    }

    public static WinningLotto publishWinningLotto(Set<Integer> lottoNumbers, Integer bonusNumber) {
        LottoTicket lottoTicket = publishLottoTicketFrom(lottoNumbers);
        LottoNumber lottoNumber = publishLottoNumberFrom(bonusNumber);
        return new WinningLotto(lottoTicket, lottoNumber);
    }
}

