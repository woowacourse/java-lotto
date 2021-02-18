package lotto.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;

public class RandomLottoGenerator implements LottoGenerator {
    public static final int MAXIMUM_BOUND = 45;

    private final Random random = new Random();

    @Override
    public LottoTicket generateLottoTicket() {
        Set<LottoNumber> numbers = new HashSet<>();
        while (numbers.size() != LottoTicket.SIZE_OF_LOTTO_NUMBERS) {
            numbers.add(getRandomLottoNumber());
        }
        return new LottoTicket (new ArrayList<>(numbers));
    }

    private LottoNumber getRandomLottoNumber() {
        return new LottoNumber(random.nextInt(MAXIMUM_BOUND) + 1);
    }

}
