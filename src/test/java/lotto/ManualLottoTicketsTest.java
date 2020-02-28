package lotto;

import domain.ManualLottoTickets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ManualLottoTicketsTest {
    private List<String[]> testNumbers = new ArrayList<>();

    @BeforeEach
    void setUp() {
        String[] a = {"1","2","3","4","5","6"};
        testNumbers.add(a);
    }

    @Test
    void 입력된_수동_로또_번호가_숫자가_아닌_경우_예외_처리() {
        String[] b = {"7","8","9","10","11","a"};
        testNumbers.add(b);

        assertThatThrownBy(() -> {
            ManualLottoTickets manualLottoTickets = new ManualLottoTickets(testNumbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
