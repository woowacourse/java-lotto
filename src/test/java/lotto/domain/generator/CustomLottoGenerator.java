package lotto.domain.generator;

import lotto.domain.LottoNumbers;
import lotto.domain.vo.LottoNumber;
import org.opentest4j.TestAbortedException;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CustomLottoGenerator implements Generator {
    private static final String TEST_LOTTO_NUMBERS_COUNT_EXCEED_EXCEPTION_MESSAGE =
            "테스트 시에는 로또 번호들을 39개까지 생성할 수 있습니다.";
    private final List<LottoNumber> lottoNumbers = Arrays.asList(LottoNumber.values());

    @Override
    public List<LottoNumbers> generateLottoNumbersGroup(int numberOfGenerating) {
        if (numberOfGenerating > 39) {
            throw new TestAbortedException(TEST_LOTTO_NUMBERS_COUNT_EXCEED_EXCEPTION_MESSAGE);
        }
        return IntStream.range(0, numberOfGenerating)
                .mapToObj(this::generateLottoNumbers)
                .collect(Collectors.toUnmodifiableList());
    }

    private LottoNumbers generateLottoNumbers(final int firstValue) {
        final Set<LottoNumber> numbers = IntStream.range(firstValue, firstValue + 6)
                .mapToObj(lottoNumbers::get)
                .collect(Collectors.toUnmodifiableSet());
        return new LottoNumbers(numbers);
    }
}
