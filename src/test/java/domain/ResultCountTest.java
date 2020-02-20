package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResultCountTest {
    @Test
    void 생성_테스트(){
        ResultCount resultCount = new ResultCount();

        Assertions.assertThat(resultCount).hasFieldOrPropertyWithValue("count", 0);
    }

    @Test
    void add시_증가_테스트() {
        ResultCount resultCount = new ResultCount();
        resultCount.add();
        resultCount.add();

        Assertions.assertThat(resultCount).hasFieldOrPropertyWithValue("count", 2);
    }

    @Test
    void equals_테스트() {
        ResultCount resultCount1 = new ResultCount();
        ResultCount resultCount2 = new ResultCount();

        Assertions.assertThat(resultCount1).isEqualTo(resultCount2);

        resultCount1.add();
        resultCount2.add();
        Assertions.assertThat(resultCount1).isEqualTo(resultCount2);
    }
}
