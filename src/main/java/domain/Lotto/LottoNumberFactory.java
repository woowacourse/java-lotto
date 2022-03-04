package domain.Lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static utils.LottoNumberConstants.MAXIMUM_LOTTO_NUMBER;
import static utils.LottoNumberConstants.MINIMUM_LOTTO_NUMBER;

public class LottoNumberFactory {

    public static final List<Integer> LOTTO_NUMBER_BOUNDARY_CACHE = new ArrayList<>();

    static {
        IntStream.rangeClosed(MINIMUM_LOTTO_NUMBER,MAXIMUM_LOTTO_NUMBER)
                .forEach(number -> LOTTO_NUMBER_BOUNDARY_CACHE.add(number));
    }

    public static List<Integer> from(List<String> numberValues) {
        return numberValues.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
