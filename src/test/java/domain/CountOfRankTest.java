package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CountOfRankTest {
    CountOfRank countOfRank;

    @BeforeEach
    void setUp() {
        countOfRank = new CountOfRank();
    }

    @Test
    void Count가_처음_생성될_때_0으로_초기화되는지_테스트() {
        assertThat(countOfRank.getCount()).isEqualTo(0);
    }

    @Test
    void Count를_올렸을_때_제대로_수치가_올라가는지_테스트() {
        countOfRank.countUp();

        assertThat(countOfRank.getCount()).isEqualTo(1);
    }
}
