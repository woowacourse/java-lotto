package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LottoFactoryTest {

    LottoFactory lottoFactory;

    @BeforeEach
    void setUp() {
        final Money money = new Money("100000000");

        lottoFactory = new LottoFactory(money);
    }

        assertThat(lottoFactory.calculateCount(money)).isEqualTo(counts);
    }
}
