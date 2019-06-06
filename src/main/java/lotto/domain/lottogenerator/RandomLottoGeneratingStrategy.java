package lotto.domain.lottogenerator;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomLottoGeneratingStrategy implements LottoGeneratingStrategy {

    @Override
    public List<Integer> generate() {
        List<Integer> allLottoNumbers = LottoNumber.getLottoNumbers();
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
