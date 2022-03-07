package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import domain.generator.NumsGenerator;
import domain.result.TicketCount;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoFactoryTest {
    private List<Integer> nums;

    private List<LottoNumber> toNumberList(List<Integer> raw) {
        return raw.stream().map(LottoNumber::getInstance).collect(Collectors.toList());
    }


    @BeforeEach
    void 로또_번호_생성() {
        nums = Arrays.asList(1, 2, 3, 4, 5, 6);
    }

    @Test
    void 일부터_육까지_여섯개_번호생성_확인() {
        Lotto lotto = Lotto.from(NumsGenerator.generate(nums));
        System.out.println(lotto);
        assertThat(lotto)
                .extracting("value")
                .isEqualTo(toNumberList(nums));
    }

    @Test
    void 당첨번호_생성_값_확인() {
        WinningLotto winningLotto = LottoFactory.createWinNums(nums, 10);
        assertThat(winningLotto)
                .extracting("lotto")
                .extracting("value")
                .isEqualTo(toNumberList(nums));
    }

    @Test
    void 당첨번호_생성_보너스_확인() {
        WinningLotto winningLotto = LottoFactory.createWinNums(nums, 10);
        assertThat(winningLotto.getBonusNumber())
                .isEqualTo(LottoNumber.getInstance(10));
    }

    @Test
    void 로또티켓_생성_검사() {
        LottoGroup lottos = LottoFactory
                .createLottos(
                        TicketCount.of(1, 1)
                        , List.of(nums));
        assertThat(lottos.get())
                .containsOnly(Lotto.from(NumsGenerator.generate(nums)));
    }
}