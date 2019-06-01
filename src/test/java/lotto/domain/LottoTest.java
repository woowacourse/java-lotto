package lotto.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {

    @Test
    void 로또_중복_번호() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(Arrays.asList(
                    LottoNumber.getInstance(1),
                    LottoNumber.getInstance(2),
                    LottoNumber.getInstance(3),
                    LottoNumber.getInstance(4),
                    LottoNumber.getInstance(6),
                    LottoNumber.getInstance(6)
                    ));
        });
    }

    @Test
    void 유효하지_않은_번호를_갖는_로또_예외() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Lotto(Arrays.asList(
                    LottoNumber.getInstance(-1),
                    LottoNumber.getInstance(2),
                    LottoNumber.getInstance(3),
                    LottoNumber.getInstance(4),
                    LottoNumber.getInstance(5),
                    LottoNumber.getInstance(6)
            ));
        });
    }

    @Test
    void 랜덤_로또_생성() {
        new Lotto(new RandomNumberGenerator().generate());
    }
}
