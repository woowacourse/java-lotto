package lotto.domain.ticket;

import lotto.domain.ticket.exception.InvalidDuplicatedNumberException;
import lotto.domain.ticket.exception.InvalidNumberCountException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LottoNumbersTest {
    @Test public void 로또_번호_갯수_초과_아래_예외() {
        assertThrows(InvalidNumberCountException.class, () ->{
            new LottoNumbers(Arrays.asList(new LottoNumber(1)));
        });
    }
    @Test
    public void 로또_번호_갯수_초과_위_예외() {
        assertThrows(InvalidNumberCountException.class, () ->{
            new LottoNumbers(Arrays.asList(new LottoNumber(1),new LottoNumber(2),new LottoNumber(3),new LottoNumber(4),new LottoNumber(5),new LottoNumber(6),new LottoNumber(7)));
        });
    }

    @Test
    public void 로또_번호_중복_예외() {
        assertThrows(InvalidDuplicatedNumberException.class, () ->{
            new LottoNumbers(Arrays.asList(new LottoNumber(1),new LottoNumber(2),new LottoNumber(3),new LottoNumber(4),new LottoNumber(5),new LottoNumber(5)));
        });
    }

}