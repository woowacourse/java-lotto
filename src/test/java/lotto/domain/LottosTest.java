package lotto.domain;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

public class LottosTest {
    
    @Test
    void init() {
        // given
        List<Lotto> lottos = new ArrayList<>();
        lottos.add(Lotto.fromNumbers(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottos.add(Lotto.fromNumbers(Arrays.asList(7, 8, 9, 10, 11, 12)));
        
        // when
        ThrowingCallable throwingCallable = () -> new Lottos(lottos);
        
        // then
        assertThatCode(throwingCallable).doesNotThrowAnyException();
    }
}
