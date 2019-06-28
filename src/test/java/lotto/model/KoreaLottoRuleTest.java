package lotto.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KoreaLottoRuleTest {
    private LottoRule rule = new KoreaLottoRule();
    private List<Integer> numList;

    @BeforeEach
    void setUp() {
        numList = new ArrayList<>();
    }

    @Test
    void isValidList() {
        numList = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertThat(rule.isValidList(numList)).isTrue();

        numList = Arrays.asList(1, 2, 3, 4, 5, 45);
        assertThat(rule.isValidList(numList)).isTrue();

        numList = Arrays.asList(0, 2, 3, 4, 5, 6);
        assertThat(rule.isValidList(numList)).isFalse();

        numList = Arrays.asList(1, 2, 3, 4, 5, 46);
        assertThat(rule.isValidList(numList)).isFalse();

        numList = Arrays.asList(1, 2, 3, 4, 5);
        assertThat(rule.isValidList(numList)).isFalse();

        numList = Arrays.asList(1, 2, 3, 4, 5, 6, 45);
        assertThat(rule.isValidList(numList)).isFalse();
    }
}