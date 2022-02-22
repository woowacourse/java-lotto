package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LottoFactory {

    private static final int MINIMUM_LOTTO_NUMBER = 1;
    private static final int MAXIMUM_LOTTO_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;

    public static List<LottoNumber> generateAutoLotto(){
        List<Integer> boundary = makeBoundary();
        Collections.shuffle(boundary);

        return boundary.stream()
                .limit(LOTTO_SIZE)
                .map(LottoNumber::new)
                .sorted(Comparator.comparing(LottoNumber::number))
                .collect(Collectors.toList());
    }

    private static List<Integer> makeBoundary() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = MINIMUM_LOTTO_NUMBER; i <= MAXIMUM_LOTTO_NUMBER ; i++) {
            numbers.add(i);
        }
        return numbers;
    }
}
