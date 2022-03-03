package domain.strategy;

import domain.LottoNumber;
import domain.LottoNumbers;
import domain.PurchaseCount;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static domain.LottoNumbers.LOTTO_NUMBERS_SIZE;

public class AutoLottoNumberStrategy implements LottoNumberStrategy {

    private static final List<LottoNumber> numbers = LottoNumber.getNumbers();

    @Override
    public List<LottoNumbers> generate(PurchaseCount count) {
        return IntStream.range(0, count.getAutoCount())
                .mapToObj(number -> createAutoLotto())
                .map(LottoNumbers::new)
                .collect(Collectors.toList());
    }

    private List<LottoNumber> createAutoLotto() {
        Collections.shuffle(numbers);
        List<LottoNumber> lottoNumbers = new ArrayList<>(numbers.subList(0, LOTTO_NUMBERS_SIZE));
        Collections.sort(lottoNumbers);

        return Collections.unmodifiableList(lottoNumbers);
    }
}
