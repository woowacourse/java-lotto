package domain.strategy;

import static domain.LottoNumber.*;
import static org.assertj.core.api.Assertions.assertThat;

import domain.LottoTicket;
import domain.strategy.GenerateStrategy;
import domain.strategy.LottoNumberGenerateStrategy;
import java.util.Collections;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumberGenerateStrategyTest {

    private final GenerateStrategy generateStrategy = new LottoNumberGenerateStrategy();

    @Test
    @DisplayName("LottoNumberGenerateStrategy가 1~45 사이의 숫자를 뽑는지 확인한다.")
    void checkGeneratedNumberRange() {
        Set<Integer> numbers = generateStrategy.generateNumbers();
        numbers.forEach(number -> assertThat(number).isBetween(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
    }

    @Test
    @DisplayName("LottoNumberGenerateStrategy가 서로 다른 숫자를 뽑는지 확인한다.")
    void checkGeneratedNumberDuplicate() {
        Set<Integer> numbers = generateStrategy.generateNumbers();
        numbers.forEach(number -> assertThat(Collections.frequency(numbers, number)).isEqualTo(1));
    }

    @Test
    @DisplayName("로또 티켓을 뽑을 때 생성되는 숫자의 갯수를 확인한다.")
    void checkGeneratedNumberCount() {
        Set<Integer> numbers = generateStrategy.generateNumbers();
        assertThat(numbers.size()).isEqualTo(LottoTicket.LOTTO_TICKET_SIZE);
    }
}