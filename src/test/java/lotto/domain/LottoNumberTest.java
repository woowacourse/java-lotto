package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class LottoNumberTest {
    @Test
    public void create() {
        assertThat(LottoNumber.of("8")).isEqualTo(LottoNumber.of(8));
    }

    @Test
    public void create_hasSpace() {
        assertThat(LottoNumber.of("8 ")).isEqualTo(LottoNumber.of(8));
    }

    @Test
    public void create_재사용() {
        assertThat(LottoNumber.of("8") == LottoNumber.of(8)).isTrue();
    }

    @Test
    public void create_1보다_작은_값() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            LottoNumber.of(0);
        });
    }

    @Test
    public void create_45보다_큰_값() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            LottoNumber.of(46);
        });
    }

    @Test
    public void create_null() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            LottoNumber.of(null);
        });
    }
}
