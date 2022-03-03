package domain.strategy;

import domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoNumbersGeneratorTest {

    @Test
    @DisplayName("자동 생성된 로또 번호는 6자리")
    void generatedLottoNumbersSize() {
        List<List<Integer>> numbers = new RandomLottoNumberStrategy()
                .generate(new PurchaseCount(new Money(1000), 0)).stream()
                .map(LottoNumbers::getLottoNumbers)
                .collect(Collectors.toList());

        assertThat((int) numbers.stream()
                .mapToLong(List::size).sum()).isEqualTo(6);
    }

    @Test
    @DisplayName("자동 생성된 로또 번호 미중복")
    void generatedLottoNumbersDistinct() {
        List<List<Integer>> numbers = new RandomLottoNumberStrategy()
                .generate(new PurchaseCount(new Money(1000), 0)).stream()
                .map(LottoNumbers::getLottoNumbers)
                .collect(Collectors.toList());

        assertThat(numbers.stream()
                .flatMap(List::stream)
                .distinct()
                .count()).isEqualTo(6);
    }

    @Test
    @DisplayName("자동 생성된 로또 번호 정렬")
    void sortLottoNumbers() {
        List<List<Integer>> numbers = new RandomLottoNumberStrategy()
                .generate(new PurchaseCount(new Money(1000), 0)).stream()
                .map(LottoNumbers::getLottoNumbers)
                .collect(Collectors.toList());

        List<Integer> flatNumbers = numbers.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        Assertions.assertThat(flatNumbers).isSorted();
    }
}
