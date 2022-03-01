package domain.strategy;

import domain.LottoNumber;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static domain.LottoTicketNumbers.LOTTO_NUMBERS_SIZE;

public class RandomLottoNumberStrategy implements LottoNumberStrategy {

    private static final List<LottoNumber> numbers = LottoNumber.getNumbers();

    @Override
    public List<LottoNumber> generate() {
        Collections.shuffle(numbers);
        return numbers.stream()
                .limit(LOTTO_NUMBERS_SIZE)
                .collect(Collectors.toList());
    }
}
