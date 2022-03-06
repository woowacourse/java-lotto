package lotto.domain.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lotto.domain.Lotto;
import lotto.domain.vo.LottoNumber;

public class AutoLottoGenerator implements LottoGenerator {

    private static final int END_EXCLUSIVE = 6;

    private static final int MIN_AMOUNT = 0;

    private final int amount;

    public AutoLottoGenerator(int amount) {
        validationAmount(amount);
        this.amount = amount;
    }

    @Override
    public List<Lotto> generateLottos() {
        return  IntStream.range(0, amount)
            .mapToObj(list -> Lotto.of(limitLottoNumbers()))
            .collect(Collectors.toList());
    }

    private List<LottoNumber> limitLottoNumbers() {
        return shuffleNumbers().stream()
            .limit(END_EXCLUSIVE)
            .collect(Collectors.toList());
    }

    private List<LottoNumber> shuffleNumbers() {
        List<LottoNumber> lottoNumbers = new ArrayList<>(LottoNumber.values());
        Collections.shuffle(lottoNumbers);
        return lottoNumbers;
    }

    private void validationAmount(int amount) {
        if (amount < MIN_AMOUNT) {
            throw new IllegalArgumentException("음수로 자동 로또를 생성할 수 없다.");
        }
    }
}
