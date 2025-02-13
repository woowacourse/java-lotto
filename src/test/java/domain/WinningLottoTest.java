package domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import constant.WinningCount;
import exception.ErrorMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class WinningLottoTest {

    @DisplayName("보너스 번호 범위 검증")
    @ParameterizedTest
    @CsvSource({"0","46","-100"})
    void validateRange(int bonusNumber){
        // given
        List<Integer> numbers  = new ArrayList<>(List.of(1,2,3,4,5,6));
        // when & then
        assertThatThrownBy(()-> new WinningLotto(numbers,bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.LOTTO_RANGE_ERROR.getMessage());
    }

    @DisplayName("보너스 번호 중복 검증")
    @Test
    void validateDuplicate(){
        // given
        List<Integer> numbers  = new ArrayList<>(List.of(1,2,3,4,5,6));
        Integer bonusNumber = 1;
        // when & then
        assertThatThrownBy(()-> new WinningLotto(numbers,bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.LOTTO_NUMBER_DUPLICATED_ERROR.getMessage());
    }

    @DisplayName("로또 결과 반환 검증")
    @ParameterizedTest
    @MethodSource
    void lottoResultTest(List<Integer> issuedLotto, WinningCount expectedWinningCount){
        WinningLotto winningLotto = new WinningLotto(List.of(1, 2, 3, 4, 5, 6), 7);
        WinningCount lottoResult = winningLotto.getLottoResult(issuedLotto);
        assertThat(lottoResult).isEqualTo(expectedWinningCount);
    }

    static Stream<Arguments> lottoResultTest(){
        return Stream.of(
                Arguments.of(List.of(11, 12, 13, 14, 15, 16), WinningCount.NONE),
                Arguments.of(List.of(1, 12, 13, 14, 15, 16), WinningCount.NONE),
                Arguments.of(List.of(1, 2, 13, 14, 15, 16), WinningCount.NONE),
                Arguments.of(List.of(1, 2, 3, 14, 15, 16), WinningCount.THREE),
                Arguments.of(List.of(1, 2, 3, 4, 15, 16), WinningCount.FOUR),
                Arguments.of(List.of(1, 2, 3, 4, 5, 16), WinningCount.FIVE),
                Arguments.of(List.of(1, 2, 3, 4, 5, 7), WinningCount.FIVE_BONUS),
                Arguments.of(List.of(1, 2, 3, 4, 5, 6), WinningCount.SIX)
        );
    }
}