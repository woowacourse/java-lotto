package lotto.model.object;

import lotto.model.BonusBall;
import lotto.model.Lotto;
import lotto.model.LottoNumber;
import lotto.model.exception.LottoNumberDuplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoTest {
        List<LottoNumber> lottoNumbers1 = new ArrayList<>();
        List<LottoNumber> lottoNumbers2 = new ArrayList<>();
        Lotto lotto1;
        Lotto lotto2;

        @BeforeEach
        void setUp() {
                lottoNumbers1.add(LottoNumber.getInstance(1));
                lottoNumbers1.add(LottoNumber.getInstance(2));
                lottoNumbers1.add(LottoNumber.getInstance(3));
                lottoNumbers1.add(LottoNumber.getInstance(4));
                lottoNumbers1.add(LottoNumber.getInstance(5));
                lottoNumbers1.add(LottoNumber.getInstance(6));

                lotto1 = new Lotto(lottoNumbers1);

                lotto2 = new Lotto(new String[]{"10", "20", "30", "4", "5", "6"});

                lottoNumbers2.add(LottoNumber.getInstance(1));
                lottoNumbers2.add(LottoNumber.getInstance(2));
                lottoNumbers2.add(LottoNumber.getInstance(3));
                lottoNumbers2.add(LottoNumber.getInstance(4));
                lottoNumbers2.add(LottoNumber.getInstance(2));
                lottoNumbers2.add(LottoNumber.getInstance(6));
        }

        @Test
        void 로또_숫자_중복_검사() {
                assertThrows(LottoNumberDuplicationException.class, ()->{
                        new Lotto(lottoNumbers2);
                });
        }

        @Test
        void 매칭_숫자_검사() {
                assertThat(lotto1.getMatchNumber(lotto2)).isEqualTo(3);
        }

        @Test
        void 보너스볼_일치_검사() {
                BonusBall bonusBall = new BonusBall(6);

                assertThat(lotto1.hasBonusBall(bonusBall)).isEqualTo(true);
        }
}
