package domain;

import static domain.properties.LottoProperties.COUNT_OF_LOTTO_NUMBERS;
import static domain.properties.LottoProperties.MAX_LOTTO_NUMBER;
import static domain.properties.LottoProperties.MIN_LOTTO_NUMBER;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class LottoGenerator {
    private static final Random random = new Random();

    public static List<LottoNumber> generate() {
        Set<LottoNumber> numbers = new HashSet<>();
        while (numbers.size() < COUNT_OF_LOTTO_NUMBERS) {
            int randomNumber = random.nextInt(MAX_LOTTO_NUMBER) + MIN_LOTTO_NUMBER;
            numbers.add(LottoNumber.of(randomNumber));
        }
        return new ArrayList<>(numbers);
    }
}
