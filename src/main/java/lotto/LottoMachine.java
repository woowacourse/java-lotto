package lotto;

import java.util.*;

public class LottoMachine {

    private static final int LOTTO_NUMBER_START_INDEX = 0;
    private static final int LOTTO_NUMBER_END_INDEX = 6;
    private static final int LOTTO_NUMBER_LOWER_BOUND = 1;
    private static final int LOTTO_NUMBER_UPPER_BOUND = 46;

    private final List<LottoNumber> numbers;

    public LottoMachine() {
        numbers = new ArrayList<>();

        for (int i = LOTTO_NUMBER_LOWER_BOUND; i < LOTTO_NUMBER_UPPER_BOUND; i++) {
            numbers.add(new LottoNumber(i));
        }
    }

    public List<Set<LottoNumber>> makeLottoTickets(int count) {
        ArrayList<Set<LottoNumber>> lottoTickets = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottoTickets.add(makeLottoTicket());
        }
        return lottoTickets;
    }

    private Set<LottoNumber> makeLottoTicket() {
        Collections.shuffle(numbers);
        return new HashSet<>(numbers.subList(LOTTO_NUMBER_START_INDEX, LOTTO_NUMBER_END_INDEX));
    }
}
