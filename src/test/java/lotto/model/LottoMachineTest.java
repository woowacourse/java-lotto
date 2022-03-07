package lotto.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import lotto.model.exception.NotEnoughMoneyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {

    @Test
    @DisplayName("로또 발급 테스트")
    void issueLottoNumbers() {
        final AtomicInteger count = new AtomicInteger(0);
        final Lotto[] lottoes = new Lotto[]{
            Lotto.create(List.of(1, 2, 3, 4, 5, 6)),
            Lotto.create(List.of(11, 12, 13, 14, 15, 16)),
            Lotto.create(List.of(21, 22, 23, 24, 25, 26))
        };

        LottoMachine lottoMachine = new LottoMachine(() -> lottoes[count.getAndIncrement()],
            new Money(3000), new Lottoes(Collections.emptyList()));
        Lottoes actual = lottoMachine.issueLotto();
        assertThat(count.get()).isEqualTo(3);
        assertThat(actual).hasSize(3);
        assertThat(actual).contains(lottoes);
    }

    @Test
    @DisplayName("로또 수동 발급 테스트")
    void registerManualLotto() {
        Lotto autoLotto = Lotto.create(List.of(1, 2, 3, 4, 5, 6));
        Lotto manualLotto1 = Lotto.create(List.of(11, 12, 13, 14, 15, 16));
        Lotto manualLotto2 = Lotto.create(List.of(21, 22, 23, 24, 25, 26));

        LottoMachine lottoMachine = new LottoMachine(() -> autoLotto, new Money(3000),
            new Lottoes(List.of(manualLotto1, manualLotto2)));
        Lottoes actual = lottoMachine.issueLotto();

        assertThat(actual).hasSize(3);
        assertThat(actual).contains(autoLotto, manualLotto1, manualLotto2);
    }

    @Test
    @DisplayName("로또 발급 개수 테스트")
    void lottoSize() {
        Lotto autoLotto = Lotto.create(List.of(1, 2, 3, 4, 5, 6));
        Lotto manualLotto1 = Lotto.create(List.of(11, 12, 13, 14, 15, 16));
        Lotto manualLotto2 = Lotto.create(List.of(21, 22, 23, 24, 25, 26));

        LottoMachine lottoMachine = new LottoMachine(() -> autoLotto, new Money(3000),
            new Lottoes(List.of(manualLotto1, manualLotto2)));

        assertThat(lottoMachine.getManualLottoesSize()).isEqualTo(2);
        assertThat(lottoMachine.getAutoLottoesSize()).isEqualTo(1);
    }

    @Test
    @DisplayName("돈이 부족할 때 수동 발급 시 예외 발생")
    void notEnoughMoney() {
        Lotto autoLotto = Lotto.create(List.of(1, 2, 3, 4, 5, 6));
        Lotto manualLotto1 = Lotto.create(List.of(11, 12, 13, 14, 15, 16));
        Lotto manualLotto2 = Lotto.create(List.of(21, 22, 23, 24, 25, 26));

        assertThatThrownBy(() -> new LottoMachine(() -> autoLotto, new Money(1000),
            new Lottoes(List.of(manualLotto1, manualLotto2))))
            .isInstanceOf(NotEnoughMoneyException.class);
    }
}
