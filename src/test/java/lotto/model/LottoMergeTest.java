package lotto.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoMergeTest {

    @Test
    @DisplayName("두개의 리스트가 병합되는지 테스트")
    void test_List_Merge() {
        List<Ticket> tickets1 = Collections.singletonList(new Ticket("1, 2, 3, 4, 5, 6"));
        List<Ticket> tickets2 = Collections.singletonList(new Ticket("1, 2, 3, 4, 5, 7"));
        assertThat(LottoMerge.merge(tickets1, tickets2)).isEqualTo(
            Arrays.asList(new Ticket("1, 2, 3, 4, 5, 6"), new Ticket("1, 2, 3, 4, 5, 7")));
    }
}
