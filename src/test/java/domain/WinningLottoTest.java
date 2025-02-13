package domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
                .hasMessage("로또 번호 범위 오류");
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
                .hasMessage("로또 번호 중복 오류");
    }
}