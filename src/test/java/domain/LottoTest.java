package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Lotto 테스트")
public class LottoTest {
    private Set<Integer> lottoNumbers;

    @BeforeEach
    void setup() {
        lottoNumbers = IntStream.rangeClosed(1, 6)
                .boxed()
                .collect(Collectors.toSet());
    }

    @Test
    @DisplayName("생성자에 6개 LottoNumber 를 전달 받으면 Lotto 객체가 생성된다.")
    void createLotto() {
        // given
        Lotto lotto = Lotto.fromRawValues(lottoNumbers);

        // when & then
        assertThat(lotto).isNotNull();
    }

    @Test
    @DisplayName("Lotto 생성시 전달된 List 길이가 6이 아니면 IAE 발생한다.")
    void createLottoWithInvalidSizeOfLottoNumbersShouldFail() {
        // given
        lottoNumbers.add(8);

        // then
        assertThatThrownBy(() -> Lotto.fromRawValues(lottoNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Lotto.ERROR_MESSAGE_FOR_INVALID_SIZE_OF_LOTTO_NUMBERS);
    }

    @Test
    @DisplayName("Lotto 는 다른 Lotto 객체를 전달 받아 같은 숫자의 수를 반환할 수 있다.")
    void lottoReturnsNumberOfSameNumberCount() {
        // given
        Lotto lotto = Lotto.fromRawValues(lottoNumbers);

        // when
        Set<Integer> newLottoNumbers = new HashSet<>(lottoNumbers);
        newLottoNumbers.remove(6);
        newLottoNumbers.add(7);
        Lotto anotherLotto = Lotto.fromRawValues(newLottoNumbers);

        // then
        assertThat(lotto.getSameNumberCount(anotherLotto)).isEqualTo(5);
    }

    @DisplayName("Lotto 에 LottoNumber 를 전달하면 해당 숫자의 포함 여부 확인할 수 있다.")
    @ParameterizedTest(name = "{0} 전달")
    @CsvSource(value = {"1,true", "6,true", "7,false"})
    void lottoContainsLottoNumberTest(int lottoNumber, boolean expected) {
        // given
        LottoNumber lottoNumber1 = LottoNumber.from(lottoNumber);

        // when
        Lotto lotto = Lotto.fromRawValues(lottoNumbers);

        // then
        assertThat(lotto.containsLottoNumber(lottoNumber1)).isEqualTo(expected);
    }

    @Test
    @DisplayName("getSortedLottoNumbers 는 오름차순 정렬된 List<LottoNumber> 를 반환한다.")
    void elementsOfGetLottosShouldSortedInAscendingOrder() {
        // given
        Lotto lotto = Lotto.fromRawValues(Set.of(5, 10, 42, 25, 3, 7));

        // when
        List<LottoNumber> actual = lotto.getSortedLottoNumbers();
        List<LottoNumber> expected = List.of(LottoNumber.from(3), LottoNumber.from(5), LottoNumber.from(7),
                LottoNumber.from(10), LottoNumber.from(25), LottoNumber.from(42));

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
