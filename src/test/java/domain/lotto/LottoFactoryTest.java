package domain.lotto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class LottoFactoryTest {
    private List<Integer> nums;

    @BeforeEach
    void 로또_번호_생성() {
        nums = Arrays.asList(1, 2, 3, 4, 5, 6);
    }

    @Test
    void 일부터_육까지_여섯개_번호생성_확인() {
        Lotto lotto = LottoFactory.createLotto(nums);
        System.out.println(lotto);
        assertThat(lotto)
                .extracting("lotto")
                .isEqualTo(Arrays.asList(
                        LottoBall.from(1),
                        LottoBall.from(2),
                        LottoBall.from(3),
                        LottoBall.from(4),
                        LottoBall.from(5),
                        LottoBall.from(6)
                ));
    }

    @Test
    void 당첨번호_생성_값_확인() {
        WinLotto winLotto = LottoFactory.createWinLotto(nums, LottoBall.from(10));
        assertThat(winLotto)
                .extracting("lotto")
                .isEqualTo(Arrays.asList(
                        LottoBall.from(1),
                        LottoBall.from(2),
                        LottoBall.from(3),
                        LottoBall.from(4),
                        LottoBall.from(5),
                        LottoBall.from(6)
                ));
    }

    @Test
    void 당첨번호_생성_보너스_확인() {
        WinLotto winLotto = LottoFactory.createWinLotto(nums, LottoBall.from(10));
        assertThat(winLotto.getBonus())
                .isEqualTo(LottoBall.from(10));
    }
}