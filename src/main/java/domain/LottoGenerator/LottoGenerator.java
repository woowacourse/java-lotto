package domain.LottoGenerator;

import domain.Lotto.Lotto;
import domain.Lotto.LottoNumber;

import java.util.List;
import java.util.stream.Collectors;

public interface LottoGenerator {

    Lotto generateLotto();

    default Lotto generateLotto(List<Integer> numbers){
        List<LottoNumber> lottoNumbers = numbers.stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toList());

        return new Lotto(lottoNumbers);
    }
}
