package lotto.domain.lottogenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotto.LottoNumber;

public class RandomLottoGenerator implements LottoGenerator {

    @Override
    public List<LottoNumber> generateLottoNumbers() {
        List<LottoNumber> lottoNumbers = getShuffledLottoAllNumbers()
            .stream()
            .map(number -> LottoNumber.valueOf(number))
            .limit(6)
            .collect(Collectors.toList());
        lottoNumbers.sort((n1, n2) -> Integer.compare(n1.getValue(), n2.getValue()));
        return lottoNumbers;
    }

    private List<Integer> getShuffledLottoAllNumbers() {
        List<Integer> lottoAllNumbers = new ArrayList<>();
        for (int i = LottoNumber.MIN + 1; i < LottoNumber.MAX; i++) {
            lottoAllNumbers.add(i);
        }
        Collections.shuffle(lottoAllNumbers);
        return lottoAllNumbers;
    }
}
