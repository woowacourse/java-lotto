package model;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    @DisplayName("생성된 로또번호에 중복이 있으면 예외를 발생한다.")
    void 생성된_로또번호에_중복이_있으면_예외를_발생한다(){
        List<Integer> duplicateNumberList = List.of(1, 1, 1, 1, 1, 1);

        assertThatThrownBy(() -> new Lotto(duplicateNumberList))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자가 중복되어서는 안됩니다.");
    }



}
