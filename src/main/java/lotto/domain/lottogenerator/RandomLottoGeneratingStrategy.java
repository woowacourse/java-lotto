package lotto.domain.lottogenerator;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class RandomLottoGeneratingStrategy implements LottoGeneratingStrategy {

    @Override
    public List<Integer> generate() {
        return Collections.unmodifiableList(createLottoNumbers(shuffledLottoNumbers()));
    }

    private static List<Integer> shuffledLottoNumbers() {
        List<Integer> allLottoNumbers = IntStream.rangeClosed(LottoNumber.MIN_BOUNDARY, LottoNumber.MAX_BOUNDARY)
                .boxed()
                .collect(toList());

        Collections.shuffle(allLottoNumbers);
        return allLottoNumbers;
    }

    private List<Integer> createLottoNumbers(List<Integer> shuffledLottoNumbers) {
        return shuffledLottoNumbers
                .stream()
                .limit(Lotto.LOTTO_NUMBER_SIZE)
                .sorted()
                .collect(toList());
    }
}
