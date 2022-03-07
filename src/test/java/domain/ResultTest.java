package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class ResultTest {

    @Test
    void 생성자_1등_검사() {
        Result result = new Result();
        result.add(Rank.of(6, false));

        assertThat(result.get().get(Rank.FIRST)).isEqualTo(1);
    }

    @Test
    void 생성자_1등_2개_검사() {
        Result result = new Result();
        result.add(Rank.of(6, false));
        result.add(Rank.of(6, false));

        assertThat(result.get().get(Rank.FIRST)).isEqualTo(2);
    }

    @Test
    void 생성자_안뽑힘_검사() {
        Result result = new Result();
        result.add(Rank.of(2, false));
        result.add(Rank.of(1, false));
        result.add(Rank.of(0, false));

        assertThat(result.get().get(Rank.NO_PRIZE)).isEqualTo(3);
    }

    @Test
    void 수익률_1_검사() {
        Result result = new Result();
        result.add(Rank.of(6, false));

        assertThat(result.getProfit(2000000000)).isEqualTo(1.0f);
    }

    @Test
    void 수익률_0_5_검사() {
        Result result = new Result();
        result.add(Rank.of(4, false));

        assertThat(result.getProfit(100000)).isEqualTo(0.5f);
    }

    @Test
    void 수익률_0_검사() {
        Result result = new Result();
        result.add(Rank.of(0, false));
        result.add(Rank.of(1, false));
        result.add(Rank.of(2, false));

        assertThat(result.getProfit(10000)).isEqualTo(0);
    }
}