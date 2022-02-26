package lotto.domain.generator;

import lotto.domain.lottonumber.Lotto;
import lotto.domain.lottonumber.vo.LottoNumber;
import org.opentest4j.TestAbortedException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.util.constants.Lotto.LAST_LOTTO_NUMBER;

public class LottoCustomGenerator implements LottoGenerator {
    private static final int MAX_GENERATING_LOTTO_NUMBERS_COUNT = 39;
    private static final String TEST_LOTTO_NUMBERS_COUNT_EXCEED_EXCEPTION_MESSAGE =
            "테스트 시에는 로또 번호들을 39개까지 생성할 수 있습니다.";
    private static final int SIZE_OF_EACH_LOTTO_NUMBERS = 6;

    private final List<LottoNumber> lottoNumbers;

    public LottoCustomGenerator() {
        lottoNumbers = IntStream.range(0, LAST_LOTTO_NUMBER)
                .mapToObj(index -> LottoNumber.from(String.valueOf(index + 1)))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<Lotto> generateLottos(int numberOfGenerating) {
        if (numberOfGenerating > MAX_GENERATING_LOTTO_NUMBERS_COUNT) {
            throw new TestAbortedException(TEST_LOTTO_NUMBERS_COUNT_EXCEED_EXCEPTION_MESSAGE);
        }
        return IntStream.range(0, numberOfGenerating)
                .mapToObj(this::generateLottoNumbers)
                .collect(Collectors.toUnmodifiableList());
    }

    private Lotto generateLottoNumbers(final int firstValue) {
        final Set<LottoNumber> numbers = IntStream.range(firstValue, firstValue + SIZE_OF_EACH_LOTTO_NUMBERS)
                .mapToObj(lottoNumbers::get)
                .collect(Collectors.toUnmodifiableSet());
        return new Lotto(numbers);
    }
}
