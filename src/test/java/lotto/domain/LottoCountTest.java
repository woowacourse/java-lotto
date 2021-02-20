package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoCountTest {

    @Test
    @DisplayName("로또 개수 객체에 제대로 값이 들어가는 지 테스트")
    public void init() {
        LottoCount count = new LottoCount(5);
        assertThat(count.get()).isEqualTo(5);
    }

    @ParameterizedTest(name = "{index} {displayName} : count = {arguments}")
    @DisplayName("로또 개수가 0 이상일 경우 객체 정상 생성")
    @ValueSource(ints = { 0, 1, 2, 3 })
    public void init_validateCount(int count) {
        assertThatCode(() ->
             new LottoCount(count)
        );
    }

    @ParameterizedTest(name = "{index} {displayName} : count = {arguments}")
    @DisplayName("로또 개수가 0 미만일 경우 예외 발생")
    @ValueSource(ints = { -1, -2, -3, -4 })
    public void  init_validateCount_thrownException(int count) {
        assertThatIllegalArgumentException().isThrownBy(() ->
            new LottoCount(count)
        );
    }

    @Test
    @DisplayName("LottoCount 객체들끼리 뺄셈을 정상적으로 하는 지 테스트")
    public void subtract() {
        LottoCount ten = new LottoCount(10);
        LottoCount three = new LottoCount(3);

        assertThat(ten.subtract(three)).isEqualTo(new LottoCount(7));
    }

    @Test
    @DisplayName("LottoCount 객체들끼리 뺄셈을 할 때 0 미만의 객체가 나오면 예외 발생")
    public void subtract_thrownException() {
        LottoCount three = new LottoCount(3);
        LottoCount ten = new LottoCount(10);

        assertThatIllegalArgumentException().isThrownBy(() ->
            three.subtract(ten)
        );
    }
}
