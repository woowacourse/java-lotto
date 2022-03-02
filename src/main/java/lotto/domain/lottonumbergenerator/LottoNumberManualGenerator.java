package lotto.domain.lottonumbergenerator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import lotto.domain.LottoNumber;

public class LottoNumberManualGenerator implements LottoNumberGenerator {

    public static final String EMPTY_LOTTO_NUMBERS = "생성할 수동 로또 번호가 없습니다.";

    private final Queue<List<LottoNumber>> lottoNumbers;

    public LottoNumberManualGenerator(List<List<Integer>> numbers) {
        lottoNumbers = createLottoNumbers(numbers);
    }

    @Override
    public List<LottoNumber> getLottoNumbers(int count) {
        if (lottoNumbers.isEmpty()) {
            throw new RuntimeException(EMPTY_LOTTO_NUMBERS);
        }
        return new ArrayList<>(lottoNumbers.poll());
    }

    private Queue<List<LottoNumber>> createLottoNumbers(List<List<Integer>> numbers) {
        return numbers.stream()
                .map(this::toLottoNumbers)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private List<LottoNumber> toLottoNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(LottoNumber::new)
                .sorted(LottoNumber::compareTo)
                .collect(Collectors.toList());
    }
}
