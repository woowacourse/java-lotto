package lotto.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author heebg
 * @version 1.0 2019-05-29
 */
public class LottoNumberTest {
    LottoNumber lottoNumber;

    @BeforeEach
    void setUp() {
        lottoNumber = new LottoNumber(12);
    }

    @Test
    void create_생성() {
        assertThat(lottoNumber).isEqualTo(new LottoNumber(12));
    }

    @Test
    void create_경계값_0_예외() {
        assertThrows(Exception.class, () -> {
            new LottoNumber(0);
        });
    }

    @Test
    void create_경계값_46_예외() {
        assertThrows(Exception.class, () -> {
            new LottoNumber(46);
        });
    }
}
