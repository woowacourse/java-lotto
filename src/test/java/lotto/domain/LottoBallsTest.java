package lotto.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class LottoBallsTest {

    @Test
    @DisplayName("6개의 로또볼을 리턴해주는지 테스")
    void generateLottoTicket() {
        Assertions.assertThat(LottoBalls.generateLottoTicket().size()).isEqualTo(6);
    }
}