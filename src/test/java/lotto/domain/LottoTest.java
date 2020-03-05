package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lotto.exception.InvalidLottoSizeException;

class LottoTest {

    @Test
    @DisplayName("유효한 로또번호로 로또 생성")
    void createWithValidLottoNumbers() {
        //given
        Set<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
            .map(LottoNumber::of)
            .collect(Collectors.toSet());

        assertThat(new Lotto(lottoNumbers)).isNotNull();
    }

    @Test
    @DisplayName("유효하지 않은 로또 번호로 로또 생성")
    void createWithInvalidLottoNumbers() {
        //given
        Set<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5)
            .map(LottoNumber::of)
            .collect(Collectors.toSet());

        assertThatThrownBy(() -> new Lotto(lottoNumbers))
            .isInstanceOf(InvalidLottoSizeException.class)
            .hasMessage("로또 번호의 개수는 6개여야 합니다");
    }

    @ParameterizedTest
    @MethodSource("provideMatchOrNoneMatchLottoNumber")
    @DisplayName("로또번호 일치 여부 체크")
    void matchLottoNumber(int value, boolean expected) {
        //given
        Set<LottoNumber> lottoNumbers = Stream.of(1, 2, 3, 4, 5, 6)
            .map(LottoNumber::of)
            .collect(Collectors.toSet());

        //when
        Lotto lotto = new Lotto(lottoNumbers);
        //then
        assertThat(lotto.contains(LottoNumber.of(value))).isEqualTo(expected);
    }

    private static Stream<Arguments> provideMatchOrNoneMatchLottoNumber() {
        return Stream.of(
            Arguments.of(1, true),
            Arguments.of(2, true),
            Arguments.of(3, true),
            Arguments.of(4, true),
            Arguments.of(5, true),
            Arguments.of(6, true),
            Arguments.of(7, false),
            Arguments.of(8, false)
        );
    }
}
