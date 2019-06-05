package lotto.domain.lottogenerator;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomLottoGeneratingStrategy implements LottoGeneratingStrategy {

    @Override
    public List<Integer> generate() {
        List<Integer> allLottoNumbers = IntStream.rangeClosed(LottoNumber.MIN_BOUNDARY, LottoNumber.MAX_BOUNDARY)
                .boxed()
                .collect(Collectors.toList());

        Collections.shuffle(allLottoNumbers);

        return Collections.unmodifiableList(createLottoNumbers(allLottoNumbers));
    }

    private List<Integer> createLottoNumbers(List<Integer> allLottoNumbers) {
        return allLottoNumbers
                .stream()
                .limit(Lotto.LOTTO_NUMBER_SIZE)
                .sorted()
                .collect(Collectors.toList());
    }
}
