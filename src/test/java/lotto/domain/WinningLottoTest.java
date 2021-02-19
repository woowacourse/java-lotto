package lotto.domain;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

public class WinningLottoTest {
    
    @Test
    @DisplayName("당첨 로또 생성 테스트")
    void init() {
        // given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        int bonusNumber = 45;
        
        // when
        ThrowingCallable throwingCallable = () -> WinningLotto.of(numbers, bonusNumber);
        
        // then
        assertThatCode(throwingCallable).doesNotThrowAnyException();
    }
}
