package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static constant.LottoConstant.*;

public class RandomLottoNumberStrategy implements LottoNumberStrategy {

    @Override
    public List<LottoNumber> generate() {
        List<LottoNumber> numbers = LottoNumber.getNumbers();
        Collections.shuffle(numbers);
        return numbers.stream()
                .limit(LOTTO_NUMBERS_SIZE)
                .collect(Collectors.toList());
    }
}
