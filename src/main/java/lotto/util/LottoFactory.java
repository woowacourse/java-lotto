package lotto.util;

import lotto.domain.AutoLotto;
import lotto.domain.Lotto;
import lotto.domain.ManualLotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoFactory {
    private static final int START = 0;
    private static final int END = 6;
    private static final int MAXIMUM_CANDIDATE_NUMBER = 45;

    private LottoFactory() {
    }

    public static AutoLotto createAutoLotto(int count) {
        return new AutoLotto(Stream.generate(() -> new Lotto(makeLottoNumbers()))
                .limit(count)
                .collect(Collectors.toList()));

    }

    public static List<Integer> makeLottoNumbers() {
        List<Integer> numbers = Stream.iterate(1, n -> n + 1)
                .limit(MAXIMUM_CANDIDATE_NUMBER)
                .collect(Collectors.toList());
        Collections.shuffle(numbers);

        List<Integer> lottoNumber = numbers.subList(START, END);
        Collections.sort(lottoNumber);

        return lottoNumber;
    }

    public static ManualLotto createManualLotto(List<String> manualPurchaseNumbers) {
        List<Lotto> manualLotto = new ArrayList<>();

        for (String input : manualPurchaseNumbers) {
            validateInputCheck(input);
            List<Integer> lottoNumbers = Arrays.stream(input.split(", "))
                    .map(s -> Integer.parseInt(s))
                    .collect(Collectors.toList());
            manualLotto.add(new Lotto(lottoNumbers));
        }
        return new ManualLotto(manualLotto);
    }

    private static void validateInputCheck(String input) {
        if (!Arrays.stream(input.split(", "))
                .allMatch(s -> s.chars().allMatch(Character::isDigit))) {
            throw new IllegalArgumentException("숫자와 , 를 이용하여 입력해주세요.");
        }
    }

}
