package lotto.domain.ticketFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;

public class RandomTicketFactory {

    public static final int LOTTO_NUMBER_MAX_LIMIT = 45;
    public static final int LOTTO_NUMBER_MIN_LIMIT = 1;
    public static Map<Integer, LottoNumber> lottoNumberFactory = new HashMap<>();

    static {
        for (int i = LOTTO_NUMBER_MIN_LIMIT; i < LOTTO_NUMBER_MAX_LIMIT; i++) {
            lottoNumberFactory.put(i, new LottoNumber(i));
        }
    }

    private RandomTicketFactory() {
    }

    public static LottoTicket makeTicket() {
        return new LottoTicket(makeLottoNumbers());
    }

    private static Set<LottoNumber> makeLottoNumbers() {
        List<Integer> numbers = new ArrayList(lottoNumberFactory.keySet());
        Set<LottoNumber> ticketNumbers = new HashSet();
        Collections.shuffle(numbers);
        for (int i = 0; i < 6; i++) {
            ticketNumbers.add(lottoNumberFactory.get(numbers.get(i)));
        }
        return ticketNumbers;
    }

}
