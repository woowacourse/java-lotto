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
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = LottoFactory.fromNumbers(numbers);
        LottoNumber bonusNumber = LottoNumber.from(45);
        
        // when
        ThrowingCallable throwingCallable = () -> WinningLotto.of(lotto, bonusNumber);
        
        // then
        assertThatCode(throwingCallable).doesNotThrowAnyException();
    }
}
