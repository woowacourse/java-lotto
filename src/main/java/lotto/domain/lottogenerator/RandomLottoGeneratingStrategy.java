package lotto.domain.lottogenerator;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomLottoGeneratingStrategy implements LottoGeneratingStrategy {

    @Override
    public List<LottoNumber> generate() {
        List<LottoNumber> allLottoNumbers = LottoNumber.getLottoNumbers();
        Collections.shuffle(allLottoNumbers);

        List<LottoNumber> lottoNumbers = createLottoNumbers(allLottoNumbers);
        Collections.sort(lottoNumbers);
        return Collections.unmodifiableList(lottoNumbers);
    }

    private List<LottoNumber> createLottoNumbers(List<LottoNumber> allLottoNumbers) {
        return allLottoNumbers
                .stream()
                .limit(Lotto.LOTTO_NUMBER_SIZE)
                .collect(Collectors.toList());
    }
}
