package lotto.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MachineTest {

    //금액입력

    @Test
    void buyTickets() {
        Machine machine = new Machine();

        assertThat(machine.buyTickets("4000"))
            .size().isEqualTo(4);
    }

}