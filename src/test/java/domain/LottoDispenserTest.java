package domain;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

import exception.LottoException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import net.bytebuddy.build.ToStringPlugin.Enhance;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.ValueSources;

public class LottoDispenserTest {

    @ParameterizedTest
    @ValueSource(strings = {"0"} )
    @DisplayName("구입_금액이_0원이면_예외가_발생한다")
    public void 구입_금액이_0원이면_예외가_발생한다(String buyMoney){
        assertThatThrownBy(() -> {
            new LottoDispenser(buyMoney);
        }).isInstanceOf(LottoException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1000"} )
    @DisplayName("구입_금액이_1000원_단위가_아니면_예외가_발생한다")
    public void 구입_금액이_1000원_단위가_아니면_예외가_발생한다(String buyMoney){
        assertThatThrownBy(() -> {
            new LottoDispenser(buyMoney);
        }).isInstanceOf(LottoException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a"} )
    @DisplayName("구입_금액이_숫자가_아니면_예외가_발생한다")
    public void 구입_금액이_숫자가_아니면_예외가_발생한다(String buyMoney){
        assertThatThrownBy(() -> {
            new LottoDispenser(buyMoney);
        }).isInstanceOf(LottoException.class);
    }

    //통합테스트

}
