package lotto.domain;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class LottoTest {
    
    @Test
    @DisplayName("로또 생성 테스트")
    void init() {
        // given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        
        // when
        ThrowingCallable throwingCallable = () -> Lotto.fromGenerator(new FixedNumberGenerator(numbers));
        
        // then
        assertThatCode(throwingCallable).doesNotThrowAnyException();
    }
    
    @Test
    @DisplayName("만약 로또 길이가 주어진 조건보다 짧다면 예외 발생")
    void init_NotMatchLength_ExceptionThrown() {
        // given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        
        // when
        ThrowingCallable throwingCallable = () -> Lotto.fromGenerator(new FixedNumberGenerator(numbers));
        
        // then
        assertThatIllegalArgumentException().isThrownBy(throwingCallable);
    }
    
    @ParameterizedTest(name = "로또가 주어진 숫자를 포함는지 테스트")
    @CsvSource(value = {"1:true", "7:false"}, delimiter = ':')
    void containsTest(int lottoNum, boolean expected) {
        // given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = Lotto.fromGenerator(new FixedNumberGenerator(numbers));
        LottoNumber lottoNumber = LottoNumber.from(lottoNum);
        
        // when
        boolean actual = lotto.contains(lottoNumber);
        
        // then
        assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    @DisplayName("두 로또 사이의 매칭되는 로또 숫자가 몇 개인지 확인")
    void countMatchingNumberTest() {
        // given
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = Lotto.fromGenerator(new FixedNumberGenerator(numbers));
        
        List<Integer> numberGroup = Arrays.asList(1, 2, 3, 4, 5, 7);
        Lotto lottoGroup = Lotto.fromGenerator(new FixedNumberGenerator(numberGroup));
        
        // when
        long mathcCount = lottoGroup.countMatchingNumber(lotto);
        
        // then
        assertThat(mathcCount).isEqualTo(5);
    }
}