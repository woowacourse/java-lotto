//package lottogame.domain.lotto;
//
//import lottogame.utils.InvalidBonusBallNumberException;
//import lottogame.utils.InvalidWinningLottoException;
//import lottogame.utils.RedundantNumbersException;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//class WinningLottoTest {
//    @Test
//    void 객체_생성() {
//        WinningLotto winningLotto = new WinningLotto("1, 2, 3, 4, 5, 6", "7");
//        assertThat(winningLotto).isEqualTo(new WinningLotto("1, 2, 3, 4, 5, 6", "7"));
//    }
//
//    @Test
//    void 중복_체크() {
//        assertThatThrownBy(() -> {
//            new WinningLotto("1, 2, 3, 4, 6, 6", "7");
//        }).isInstanceOf(RedundantNumbersException.class);
//        assertThatThrownBy(() -> {
//            new WinningLotto("1, 2, 3, 4, 5, 6", "6");
//        }).isInstanceOf(RedundantNumbersException.class);
//    }
//
//    @Test
//    void 범위를_벗어난_당첨_로또_입력() {
//        assertThatThrownBy(() -> {
//            new WinningLotto("1, 2, 3, 48, 6, 9", "7");
//        }).isInstanceOf(InvalidWinningLottoException.class);
//    }
//
//    @Test
//    void 범위를_벗어난_보너스_볼_입력() {
//        assertThatThrownBy(() -> {
//            new WinningLotto("1, 2, 3, 4, 6, 6", "49");
//        }).isInstanceOf(InvalidBonusBallNumberException.class);
//    }
//}
