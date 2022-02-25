package domain.LottoGenerator;

import domain.Lotto.Lotto;
import domain.Lotto.LottoNumber;

<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

<<<<<<< HEAD
public class AutoLottoGenerator extends LottoGeneratorAdpater {
=======
public class AutoLottoGenerator implements LottoGenerator {
>>>>>>> d5f0ef8 (refactor: 패키지 분리)

    private static final int LOTTO_SIZE = 6;

    @Override
<<<<<<< HEAD
    public Lotto generateLotto() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.values());
        Collections.shuffle(lottoNumbers);

        List<LottoNumber> collect = lottoNumbers.stream()
                .limit(LOTTO_SIZE)
                .sorted(Comparator.comparing(LottoNumber::getNumber))
                .collect(Collectors.toList());

        return new Lotto(collect);
=======
    public Lotto generateLotto(List<Integer> numbers) {
        Collections.shuffle(numbers);

        List<LottoNumber> lottoNumbers = numbers.stream()
                .limit(LOTTO_SIZE)
                .map(LottoNumber::new)
                .sorted(Comparator.comparing(LottoNumber::getNumber))
                .collect(Collectors.toList());

        return new Lotto(lottoNumbers);
>>>>>>> d5f0ef8 (refactor: 패키지 분리)
    }
}
