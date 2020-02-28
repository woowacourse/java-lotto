package lotto;

import domain.ManualLottoTickets;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ManualLottoTicketsTest {

    @Test
    void 입력된_수동_로또_번호가_숫자가_아닌_경우_예외_처리() {
        List<List<String>> testNumbers = new ArrayList<>();
        List<String> a = Arrays.asList("1","2","3","4","5","a");
        System.out.println(a);
        testNumbers.add(a);

        assertThatThrownBy(() -> {
            ManualLottoTickets manualLottoTickets = new ManualLottoTickets(testNumbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 입력된_수동_로또_번호가_1이상_45이하가_아닌_경우_예외_처리() {
        List<List<String>> testNumbers = new ArrayList<>();
        List<String> a = Arrays.asList("1","2","3","4","5","46");
        System.out.println(a);
        testNumbers.add(a);

        assertThatThrownBy(() -> {
            ManualLottoTickets manualLottoTickets = new ManualLottoTickets(testNumbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 입력된_수동_로또_번호가_6개가_아닌_경우_예외_처리() {
        List<List<String>> testNumbers = new ArrayList<>();
        List<String> a = Arrays.asList("1","2","3","4","5");
        System.out.println(a);
        testNumbers.add(a);

        assertThatThrownBy(() -> {
            ManualLottoTickets manualLottoTickets = new ManualLottoTickets(testNumbers);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
