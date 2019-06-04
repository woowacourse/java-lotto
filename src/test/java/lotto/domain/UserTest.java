package lotto.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {


    @Test
    void 음의_금액을_입력_검사(){
        assertThrows(InvalidMoneyException.class, () -> new User(new Money(-1000),0));
    }

    @Test
    void 천원_단위_아닌_금액_입력_검사(){
        assertThrows(InvalidMoneyException.class, ()-> new User(new Money(1),0));
    }

    @Test
    void 사용자의_돈만큼_로또_생성_확인(){
        assertThat(new User(new Money(5000),0).getUserLottos().size()).isEqualTo(5);
    }

    @Test
    void 수동_로또금액이_전체금액보다_크면_예외_검사(){
        assertThrows(InvalidCountOfManualLottoException.class, ()-> new User(new Money(3000),4));
    }

}
