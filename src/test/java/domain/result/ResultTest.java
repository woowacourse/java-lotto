package domain.result;

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

        assertThat(result.get().get(Rank.NONE)).isEqualTo(3);
    }

    @Test
    void 일등_2명_상금_일치_검사() {
        Result result = new Result();
        result.add(Rank.of(6, false));
        result.add(Rank.of(6, false));

        assertThat(result.getPrize()).isEqualTo(4000000000L);
    }

    @Test
    void 꼴등_셋_상금_일치_검사() {
        Result result = new Result();
        result.add(Rank.of(0, false));
        result.add(Rank.of(1, false));
        result.add(Rank.of(2, false));

        assertThat(result.getPrize()).isEqualTo(0);
    }
}