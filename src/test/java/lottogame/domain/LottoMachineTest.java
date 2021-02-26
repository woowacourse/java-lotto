package lottogame.domain;

import lottogame.domain.dto.LottoDto;
import lottogame.domain.lotto.Lotto;
import lottogame.domain.lotto.LottoNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoMachineTest {
    private LottoMachine lottoMachine;

    @BeforeEach
    void setUp() {
        lottoMachine = new LottoMachine();
    }

    @DisplayName("로또가 정상 구매 되는 지 확인")
    @Test
    void 로또_구매_기능_테스트() {
        List<Lotto> lottos = lottoMachine.buyAutoTicket(4);
        assertThat(lottos).hasSize(4);
    }

    @DisplayName("로또 생성 시 중복 숫자를 방지하는 기능이 잘 동작하는 지 확인")
    @Test
    void 로또_생성_기능_테스트() {
        List<Lotto> lottos = lottoMachine.buyAutoTicket(3);
        assertThat(lottos).hasSize(3);

        for (Lotto lotto : lottos) {
            List<LottoNumber> lottoNumbers = lotto.values();
            assertThat(lottoNumbers.stream().distinct().count()).isEqualTo(lottoNumbers.size());
        }
    }

    @DisplayName("수동 로또 입력 후 정상 로또 객체 반환 테스트")
    @Test
    void 수동_로또_입력_후_객체_생성_테스트() {
        List<LottoNumber> manualLottoNumber1 = LottoNumber.of(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<LottoNumber> manualLottoNumber2 = LottoNumber.of(Arrays.asList(2, 3, 4, 5, 6, 7));

        List<LottoDto> lottoDtos = Arrays.asList(
                new LottoDto(manualLottoNumber1),
                new LottoDto(manualLottoNumber2)
        );

        List<Lotto> results = lottoMachine.makeManualLotto(lottoDtos);
        assertThat(results).containsExactly(new Lotto(manualLottoNumber1), new Lotto(manualLottoNumber2));
    }
}