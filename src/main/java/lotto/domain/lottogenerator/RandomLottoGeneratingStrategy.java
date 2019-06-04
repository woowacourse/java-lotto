package lotto.domain.lottogenerator;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomLottoGeneratingStrategy implements LottoGeneratingStrategy {

    @Override
    public List<LottoNumber> generate() {
        List<LottoNumber> allLottoNumbers = LottoNumber.getLottoNumbers();
        Collections.shuffle(allLottoNumbers);

        return Collections.unmodifiableList(createLottoNumbers(allLottoNumbers));
    }

    private List<LottoNumber> createLottoNumbers(List<LottoNumber> allLottoNumbers) {
        return allLottoNumbers
                .stream()
                .limit(Lotto.LOTTO_NUMBER_SIZE)
                .sorted()
                .collect(Collectors.toList());
    }
}
