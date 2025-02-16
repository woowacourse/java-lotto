package domain;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.LottoException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoDispenserTest {

    @ParameterizedTest
    @ValueSource(strings = {"0"} )
    @DisplayName("구입_금액이_0원이면_예외가_발생한다")
    public void 구입_금액이_0원이면_예외가_발생한다(String buyMoney){
        assertThatThrownBy(() -> {
            new Money(buyMoney);
        }).isInstanceOf(LottoException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"500"} )
    @DisplayName("구입_금액이_1000원_단위가_아니면_예외가_발생한다")
    public void 구입_금액이_1000원_단위가_아니면_예외가_발생한다(String buyMoney){
        assertThatThrownBy(() -> {
            new Money(buyMoney);
        }).isInstanceOf(LottoException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a"} )
    @DisplayName("구입_금액이_숫자가_아니면_예외가_발생한다")
    public void 구입_금액이_숫자가_아니면_예외가_발생한다(String buyMoney){
        assertThatThrownBy(() -> {
            new Money(buyMoney);
        }).isInstanceOf(LottoException.class);
    }

}
