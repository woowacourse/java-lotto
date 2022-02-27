package domain.Lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static utils.LottoNumberConstants.MAXIMUM_LOTTO_NUMBER;
import static utils.LottoNumberConstants.MINIMUM_LOTTO_NUMBER;

public class LottoNumberFactory {

    public static List<Integer> makeBoundary() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = MINIMUM_LOTTO_NUMBER; i <= MAXIMUM_LOTTO_NUMBER; i++) {
            numbers.add(i);
        }
        return numbers;
    }

    public static List<Integer> from(List<String> numberValues) {
        return numberValues.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
