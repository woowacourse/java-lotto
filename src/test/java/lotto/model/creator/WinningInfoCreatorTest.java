package lotto.model.creator;

import lotto.model.exception.WinningLottoBonusBallDuplicationException;
import lotto.model.object.BonusBall;
import lotto.model.object.Lotto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WinningInfoCreatorTest {
        @Test
        void 당첨_로또_보너스볼_중복_검사() {
                Lotto winningLotto = LottoCreator.create(new String[]{"1","2","3","4","5","6"});
                BonusBall bonusBall = BonusBallCreator.create(3);

                assertThrows(WinningLottoBonusBallDuplicationException.class, ()->{
                   WinningInfoCreator.create(winningLotto, bonusBall);
                });
        }
}
