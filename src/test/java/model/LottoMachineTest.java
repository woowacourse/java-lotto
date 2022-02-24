package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    @Test
    void issueLottoNumbers() {
        final AtomicInteger count = new AtomicInteger(0);
        final LottoNumbers[] lottoNumbers = new LottoNumbers[]{
            new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
            new LottoNumbers(List.of(11, 12, 13, 14, 15, 16)),
            new LottoNumbers(List.of(21, 22, 23, 24, 25, 26))
        };

        LottoMachine lottoMachine = new LottoMachine(() -> lottoNumbers[count.getAndIncrement()]);

        List<LottoNumbers> actual = lottoMachine.issueLotto(new Money(3000));
        assertThat(count.get()).isEqualTo(3);
        assertThat(actual).hasSize(3);
        assertThat(actual).contains(lottoNumbers);
    }
}
