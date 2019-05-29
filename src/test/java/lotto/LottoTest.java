package lotto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class LottoTest {

    @Test
    public void testEquals() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        Lotto lotto = Lotto.of(numbers);
        Lotto another = Lotto.of(numbers);
        Lotto other = Lotto.of(numbers);

        assertThat(lotto.equals(another)).isTrue();

        assertThat(lotto.equals(other)).isTrue();
        assertThat(other.equals(lotto)).isTrue();

        assertThat(lotto.equals(another)).isTrue();
        assertThat(another.equals(other)).isTrue();
        assertThat(lotto.equals(other)).isTrue();

        assertThat(lotto.equals(null)).isFalse();
    }

    @Test
    public void null로_생성할때_예외발생_검사() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Lotto.of(null);
        });
    }

    @Test
    public void 중복된_숫자_리스트가_입력됬을때_예외발생_검사() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 5);
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Lotto.of(numbers);
        });
    }

    @Test
    public void 숫자_리스트의_크기가_6초과일때_예외발생_검사() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Lotto.of(numbers);
        });
    }

    @Test
    public void 숫자_리스트의_크기가_6미만일때_예외발생_검사() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            Lotto.of(numbers);
        });
    }

    @Test
    public void 로또_숫자가_모두_같을때() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> anotherNumbers = Arrays.asList(2, 3, 4, 5, 6, 1);
        Lotto lotto = Lotto.of(numbers);
        Lotto another = Lotto.of(anotherNumbers);

        assertThat(lotto.matchCount(another)).isEqualTo(6);
    }

    @Test
    public void 로또_숫자가_5개_같을때() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> anotherNumbers = Arrays.asList(2, 3, 4, 5, 6, 7);
        Lotto lotto = Lotto.of(numbers);
        Lotto another = Lotto.of(anotherNumbers);

        assertThat(lotto.matchCount(another)).isEqualTo(5);
    }

    @Test
    public void 로또_숫자가_4개_같을때() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> anotherNumbers = Arrays.asList(2, 3, 4, 5, 8, 7);
        Lotto lotto = Lotto.of(numbers);
        Lotto another = Lotto.of(anotherNumbers);

        assertThat(lotto.matchCount(another)).isEqualTo(4);
    }

    @Test
    public void 로또_숫자가_3개_같을때() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> anotherNumbers = Arrays.asList(12, 13, 4, 5, 6, 15);
        Lotto lotto = Lotto.of(numbers);
        Lotto another = Lotto.of(anotherNumbers);

        assertThat(lotto.matchCount(another)).isEqualTo(3);
    }

    @Test
    public void 로또_숫자가_2개_같을때() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> anotherNumbers = Arrays.asList(12, 13, 4, 5, 16, 15);
        Lotto lotto = Lotto.of(numbers);
        Lotto another = Lotto.of(anotherNumbers);

        assertThat(lotto.matchCount(another)).isEqualTo(2);
    }

    @Test
    public void 로또_숫자가_1개_같을때() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> anotherNumbers = Arrays.asList(12, 23, 4, 14, 25, 15);
        Lotto lotto = Lotto.of(numbers);
        Lotto another = Lotto.of(anotherNumbers);

        assertThat(lotto.matchCount(another)).isEqualTo(1);
    }

    @Test
    public void 로또_숫자가_하나도_같은게_없을때() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> anotherNumbers = Arrays.asList(12, 23, 14, 25, 16, 15);
        Lotto lotto = Lotto.of(numbers);
        Lotto another = Lotto.of(anotherNumbers);

        assertThat(lotto.matchCount(another)).isEqualTo(0);
    }
}