package domain.LottoGenerator;

import domain.Lotto.Lotto;
import domain.Lotto.LottoNumber;
import domain.LottoGenerator.LottoGenerator;

import java.util.List;
import java.util.stream.Collectors;

public class WinningLottoGenerator implements LottoGenerator {

    @Override
    public Lotto generateLotto(List<Integer> numbers) {
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::new)
                .collect(Collectors.toList());

        return new Lotto(lottoNumbers);
    }
}
