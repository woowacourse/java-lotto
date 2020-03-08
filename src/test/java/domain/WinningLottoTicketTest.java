package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class WinningLottoTicketTest {
    private LottoTicket winningLottoTicket;

    @BeforeEach
    private void setUp() {
        winningLottoTicket = new LottoTicket(new ArrayList(
                Arrays.asList(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)
                )
        ));
    }

    @DisplayName("중복된 보너스 번호를 넣었을 때 예외 출력 테스트")
    @Test
    void WinningLottoTicketConstructorWithInvalidInput() {
        LottoNumber bonusBall = LottoNumbers.getLottoNumber(6);

        Assertions.assertThatThrownBy(() -> {
            new WinningLottoTicket(winningLottoTicket, bonusBall);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("WinningLottoTicket 에 정상적인 값을 넣었을 때 생성자 테스트")
    @Test
    void initializeBonusBallTest() {
        LottoNumber bonusBall = LottoNumbers.getLottoNumber(7);

        Assertions.assertThat(new WinningLottoTicket(winningLottoTicket, bonusBall)).isInstanceOf(
                WinningLottoTicket.class);
    }
}
