package lotto.domain;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class LottoNumberTest {
    
    @ParameterizedTest(name = "범위 안의 숫자를 입력할 경우 정상적으로 로또 번호 생성")
    @ValueSource(ints = {1, 2, 3, 43, 44, 45})
    public void initTest(int lottoNum) {
        
        // when
        ThrowingCallable throwingCallable = () -> LottoNumber.from(lottoNum);
        
        // then
        assertThatCode(throwingCallable).doesNotThrowAnyException();
    }
    
    @ParameterizedTest(name = "범위 외의 숫자를 입력할 경우 예외 발생")
    @ValueSource(ints = {-2, -1, 0, 46, 47, 48})
    public void init_OutOfBoundsNumber_ExceptionThrown(int lottoNum) {
        
        // when
        ThrowingCallable throwingCallable = () -> LottoNumber.from(lottoNum);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(throwingCallable);
    }
}
