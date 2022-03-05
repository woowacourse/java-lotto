package lotto.domain.lottonumbergenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;

public class LottoNumberManualGenerator implements LottoNumberGenerator {

    public static final String ERROR_LOTTO_SIZE = "생성할 로또 개수와 로또 숫자 개수가 다릅니다.";

    private final List<List<LottoNumber>> lottoNumbers;

    public LottoNumberManualGenerator(List<List<Integer>> numbers) {
        lottoNumbers = createLottoNumbers(numbers);
    }

    @Override
    public List<List<LottoNumber>> getLottoNumbersBy(int count) {
        if (lottoNumbers.size() != count) {
            throw new RuntimeException(ERROR_LOTTO_SIZE);
        }
        return new ArrayList<>(lottoNumbers);
    }

    private List<List<LottoNumber>> createLottoNumbers(List<List<Integer>> numbers) {
        return numbers.stream()
                .map(this::toLottoNumbers)
                .collect(Collectors.toList());
    }

    private List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::valueOf)
                .sorted(LottoNumber::compareTo)
                .collect(Collectors.toList());
    }
}
