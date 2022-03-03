package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.NumsGenerator;

@SuppressWarnings("NonAsciiCharacters")
class LottoFactoryTest {
    private List<Integer> nums;

    @BeforeEach
    void 로또_번호_생성() {
        nums = Arrays.asList(1, 2, 3, 4, 5, 6);
    }

    @Test
    void 일부터_육까지_여섯개_번호생성_확인() {
        Lotto lotto = Lotto.from(NumsGenerator.generate(nums));
        System.out.println(lotto);
        assertThat(lotto)
                .extracting("lotto")
                .isEqualTo(Arrays.asList(
                        LottoNumber.getInstance(1),
                        LottoNumber.getInstance(2),
                        LottoNumber.getInstance(3),
                        LottoNumber.getInstance(4),
                        LottoNumber.getInstance(5),
                        LottoNumber.getInstance(6)
                ));
    }

    @Test
    void 당첨번호_생성_값_확인() {
        WinNumbers winNumbers = LottoFactory.createWinNums(nums, 10);
        assertThat(winNumbers)
                .extracting("lotto")
                .extracting("lotto")
                .isEqualTo(Arrays.asList(
                        LottoNumber.getInstance(1),
                        LottoNumber.getInstance(2),
                        LottoNumber.getInstance(3),
                        LottoNumber.getInstance(4),
                        LottoNumber.getInstance(5),
                        LottoNumber.getInstance(6)
                ));
    }

    @Test
    void 당첨번호_생성_보너스_확인() {
        WinNumbers winNumbers = LottoFactory.createWinNums(nums, 10);
        assertThat(winNumbers.getBonus())
                .isEqualTo(LottoNumber.getInstance(10));
    }

    @Test
    void 로또티켓_생성_검사() {
        List<Lotto> lottos = LottoFactory
                .createLottos(LottoTicketCount
                                .of(1, 1)
                        , List.of(nums));
        assertThat(lottos)
                .containsOnly(Lotto.from(NumsGenerator.generate(nums)));
    }
}