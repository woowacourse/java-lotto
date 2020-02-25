package domain.lottonumber;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("번호 일급 컬렉션 테스트")
public class LottoNumbersTest {

    @Test
    @DisplayName("번호 컬렉션끼리 겹치는 번호 개수 확인")
    void findNumberOfMatchingNumbers() {
        LottoNumbers numbers1 = createLottoNumbers(1, 2, 3, 4, 5, 6);
        LottoNumbers numbers2 = createLottoNumbers(4, 5, 6, 7, 8, 9);

        assertThat(numbers1.findNumberOfMatchingNumbers(numbers2)).isEqualTo(3);
    }

    private LottoNumbers createLottoNumbers(int number1, int number2, int number3, int number4, int number5, int number6) {
        Set<LottoNumber> numbers = Arrays.asList(number1, number2, number3, number4, number5, number6).stream()
                .map(LottoNumber::valueOf)
                .collect(Collectors.toSet());

        return new LottoNumbers(numbers);
    }
}