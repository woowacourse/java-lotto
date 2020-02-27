package lotto;

import lotto.domain.ticket.number.LottoNumber;
import lotto.domain.ticket.number.LottoNumberFactory;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class LottoHelper {
    public static Set<LottoNumber> lottoNumbers(int... numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .map(LottoNumberFactory::findLottoBallByNumber)
                .collect(Collectors.toSet());
    }

    public static Set<Integer> numbers(int... numbers) {
        return Arrays.stream(numbers)
                .boxed()
                .collect(Collectors.toSet());
    }
}
