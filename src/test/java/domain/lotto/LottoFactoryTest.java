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
        Lotto lotto = LottoFactory.createLotto(NumsGenerator.generate(nums));
        System.out.println(lotto);
        assertThat(lotto)
                .extracting("lotto")
                .isEqualTo(Arrays.asList(
                        LottoNumber.from(1),
                        LottoNumber.from(2),
                        LottoNumber.from(3),
                        LottoNumber.from(4),
                        LottoNumber.from(5),
                        LottoNumber.from(6)
                ));
    }

    @Test
    void 당첨번호_생성_값_확인() {
        WinNumbers winNumbers = LottoFactory.createWinNums(NumsGenerator.generate(nums), LottoNumber.from(10));
        assertThat(winNumbers)
                .extracting("lotto")
                .extracting("lotto")
                .isEqualTo(Arrays.asList(
                        LottoNumber.from(1),
                        LottoNumber.from(2),
                        LottoNumber.from(3),
                        LottoNumber.from(4),
                        LottoNumber.from(5),
                        LottoNumber.from(6)
                ));
    }

    @Test
    void 당첨번호_생성_보너스_확인() {
        WinNumbers winNumbers = LottoFactory.createWinNums(NumsGenerator.generate(nums), LottoNumber.from(10));
        assertThat(winNumbers.getBonus())
                .isEqualTo(LottoNumber.from(10));
    }
}