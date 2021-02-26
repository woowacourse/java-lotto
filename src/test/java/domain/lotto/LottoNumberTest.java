package domain.lotto;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoNumberTest {

    @DisplayName("객체 생성 성공 : 1 ~ 45 인 값 입력")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 45})
    void of_validValue_successful(final int value) {
        assertThatCode(() -> LottoNumber.of(value))
                .doesNotThrowAnyException();
    }

    @DisplayName("객체 생성 실패 : 1 ~ 45 가 아닌 값 입력")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void of_outOfRange_exceptionThrown(final int value) {
        // TODO
    }

    @DisplayName("로또 넘버 리스트 생성 성공 : 사이즈 확인")
    @ParameterizedTest
    @ValueSource(ints = {6, 7, 8})
    void generateList_size_successful(final int value) {
        assertThat(LottoNumber.generateList(value).size()).isEqualTo(value);
    }

    @DisplayName("로또 넘버 리스트 생성 성공 : 중복 없음")
    @ParameterizedTest
    @ValueSource(ints = {6, 7, 8})
    void generateList_noDuplicate_successful(final int value) {
        List<LottoNumber> numbers = LottoNumber.generateList(value);
        assertThat(numbers.size()).isEqualTo(new HashSet<>(numbers).size());
    }

    @DisplayName("동일한 객체 비교")
    @Test
    void equals() {
        final LottoNumber lottoNumber = LottoNumber.of(1);
        assertEquals(lottoNumber, LottoNumber.of(1));
    }
}
