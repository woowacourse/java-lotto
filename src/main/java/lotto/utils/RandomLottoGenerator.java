package lotto.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import lotto.domain.LottoNumber;
import lotto.domain.LottoTicket;

public class RandomLottoGenerator implements LottoGenerator {
    private final Random random = new Random();

    @Override
    public LottoTicket generateLottoTicket() {
        Set<LottoNumber> numbers = new HashSet<>();
        while (numbers.size() != LottoTicket.SIZE_OF_LOTTO_NUMBERS) {
            numbers.add(getRandomLottoNumber());
        }
        return new LottoTicket(new ArrayList<>(numbers));
    }

    private LottoNumber getRandomLottoNumber() {
        return new LottoNumber(random.nextInt(LottoNumber.MAXIMUM_NUMBER) + 1);
    }

}
