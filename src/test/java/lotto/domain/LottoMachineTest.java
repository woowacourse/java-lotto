package lotto.domain;

import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoMachine;
import lotto.domain.lotto.Lottos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMachineTest {

    private LottoMachine lottoMachine;

    @BeforeEach
    void create() {
        lottoMachine = new LottoMachine();
    }

    @DisplayName("로또 머신으로 로또 생성 테스트")
    @Test
    void createAndPutLotto() {
        List<Lotto> lotto = new ArrayList<>();
        String numbers = "1,2,3,4,5,45";
        List<Integer> lottoNumbers = Arrays.asList(1,2,3,4,5,45);

        assertThat(lottoMachine.createAndPutLotto(lotto, numbers))
                .isEqualTo(Collections.singletonList(new Lotto(lottoNumbers)));
    }
}
