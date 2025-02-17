package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoPrizeTest {

    @DisplayName("로또 0 Hit, Bonus False 등수 확인")
    @Test
    public void lottoPrize_0Hit_BonusFalse() {
        LottoPrize lottoPrize = LottoPrize.findLottoPrize(0, false);
        assertEquals(LottoPrize.MISS, lottoPrize);
    }

    @DisplayName("로또 0 Hit, Bonus True 등수 확인")
    @Test
    public void lottoPrize_0Hit_BonusTrue() {
        LottoPrize lottoPrize = LottoPrize.findLottoPrize(0, true);
        assertEquals(LottoPrize.MISS, lottoPrize);
    }

    @DisplayName("로또 1 Hit, Bonus False 등수 확인")
    @Test
    public void lottoPrize_1Hit_BonusFalse() {
        LottoPrize lottoPrize = LottoPrize.findLottoPrize(1, false);
        assertEquals(LottoPrize.MISS, lottoPrize);
    }

    @DisplayName("로또 1 Hit, Bonus True 등수 확인")
    @Test
    public void lottoPrize_1Hit_BonusTrue() {
        LottoPrize lottoPrize = LottoPrize.findLottoPrize(1, true);
        assertEquals(LottoPrize.MISS, lottoPrize);
    }

    @DisplayName("로또 2 Hit, Bonus False 등수 확인")
    @Test
    public void lottoPrize_2Hit_BonusFalse() {
        LottoPrize lottoPrize = LottoPrize.findLottoPrize(2, false);
        assertEquals(LottoPrize.MISS, lottoPrize);
    }

    @DisplayName("로또 2 Hit, Bonus True 등수 확인")
    @Test
    public void lottoPrize_2Hit_BonusTrue() {
        LottoPrize lottoPrize = LottoPrize.findLottoPrize(2, true);
        assertEquals(LottoPrize.MISS, lottoPrize);
    }

    @DisplayName("로또 3 Hit, Bonus False 등수 확인")
    @Test
    public void lottoPrize_3Hit_BonusFalse() {
        LottoPrize lottoPrize = LottoPrize.findLottoPrize(3, false);
        assertEquals(LottoPrize.FIFTH, lottoPrize);
    }

    @DisplayName("로또 3 Hit, Bonus True 등수 확인")
    @Test
    public void lottoPrize_3Hit_BonusTrue() {
        LottoPrize lottoPrize = LottoPrize.findLottoPrize(3, true);
        assertEquals(LottoPrize.FIFTH, lottoPrize);
    }

    @DisplayName("로또 4 Hit, Bonus False 등수 확인")
    @Test
    public void lottoPrize_4Hit_BonusFalse() {
        LottoPrize lottoPrize = LottoPrize.findLottoPrize(4, false);
        assertEquals(LottoPrize.FOURTH, lottoPrize);
    }

    @DisplayName("로또 4 Hit, Bonus True 등수 확인")
    @Test
    public void lottoPrize_4Hit_BonusTrue() {
        LottoPrize lottoPrize = LottoPrize.findLottoPrize(4, true);
        assertEquals(LottoPrize.FOURTH, lottoPrize);
    }

    @DisplayName("로또 5 Hit, Bonus False 등수 확인")
    @Test
    public void lottoPrize_5Hit_BonusFalse() {
        LottoPrize lottoPrize = LottoPrize.findLottoPrize(5, false);
        assertEquals(LottoPrize.THIRD, lottoPrize);
    }

    @DisplayName("로또 5 Hit, Bonus True 등수 확인")
    @Test
    public void lottoPrize_5Hit_BonusTrue() {
        LottoPrize lottoPrize = LottoPrize.findLottoPrize(5, true);
        assertEquals(LottoPrize.SECOND, lottoPrize);
    }

    @DisplayName("로또 6 Hit, Bonus False 등수 확인")
    @Test
    public void lottoPrize_6Hit_BonusFalse() {
        LottoPrize lottoPrize = LottoPrize.findLottoPrize(6, false);
        assertEquals(LottoPrize.FIRST, lottoPrize);
    }

    @DisplayName("로또 6 Hit, Bonus True 등수 확인")
    @Test
    public void lottoPrize_6Hit_BonusTrue() {
        LottoPrize lottoPrize = LottoPrize.findLottoPrize(6, true);
        assertEquals(LottoPrize.FIRST, lottoPrize);
    }
}
