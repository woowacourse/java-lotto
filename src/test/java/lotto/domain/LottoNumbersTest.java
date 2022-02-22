package lotto.domain;

import lotto.domain.vo.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoNumbersTest {
    @Test
    @DisplayName("로또 번호들 간에 중복이 있으면 예외를 발생시킨다.")
    void create_ExceptionByDuplicatedLottoNumbers() {
        //given
        final List<String> duplicatedNumberValues = Arrays.asList("1", "1", "2", "3", "4", "5");
        final String expectedExceptionMessage = "같은 줄 로또 번호 간에 중복이 존재합니다.";
        //when then
        assertThatThrownBy(() -> new LottoNumbers(duplicatedNumberValues))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedExceptionMessage);
    }

    @Test
    @DisplayName("로또 숫자들을 오름차순으로 정렬한다.")
    void create_SortingAscending() {
        //given
        final List<String> numbers = Arrays.asList("7", "4", "5", "3", "6", "2");
        final LottoNumbers lottoNumbers = new LottoNumbers(numbers);
        final List<String> otherNumbers = Arrays.asList("2", "3", "6", "7", "4", "5");
        final LottoNumbers otherLottoNumbers = new LottoNumbers(otherNumbers);
        final LottoNumber expectedFirstLottoNumber = LottoNumber.from("2");
        //when
        final LottoNumber actualFirstLottoNumber = lottoNumbers.getValues().get(0);
        //then
        assertThat(lottoNumbers).isEqualTo(otherLottoNumbers);
        assertThat(actualFirstLottoNumber).isEqualTo(expectedFirstLottoNumber);
    }

    @Test
    @DisplayName("로또 한 줄에서의 숫자 개수가 6개가 아니면 예외를 발생시킨다.")
    void create_ExceptionByNotSixCountOfNumbers() {
        //given
        final List<String> invalidCountNumbers = Arrays.asList("1", "2", "3", "4", "5");
        final String expectedExceptionMessage = "로또 숫자는 한 줄에 6개여야 합니다.";
        //when then
        assertThatThrownBy(() -> new LottoNumbers(invalidCountNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(expectedExceptionMessage);
    }
}
