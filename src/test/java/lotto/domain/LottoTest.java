package lotto.domain;

import lotto.domain.exception.CountOfNumberException;
import lotto.domain.exception.NumberBoundException;
import lotto.domain.exception.NumberDuplicationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class LottoTest {
    private static final List<Integer> NUMBERS = Arrays.asList(1, 2, 3, 4, 5, 6);

    private LottoFactory lottoFactory;

    @BeforeEach
    public void setUp() {
        lottoFactory = new LottoFactory();
    }

    @Test
    public void equals_재귀적인지_확인() {
        
        Lotto lotto = lottoFactory.create(NUMBERS);
        assertThat(lotto.equals(lotto)).isTrue();

        assertThat(lotto.equals(null)).isFalse();
    }

    @Test
    public void equals_대칭적인지_확인() {
        
        Lotto lotto = lottoFactory.create(NUMBERS);
        Lotto another = lottoFactory.create(NUMBERS);

        assertThat(lotto.equals(another)).isTrue();
        assertThat(another.equals(lotto)).isTrue();
    }

    @Test
    public void equals_이행적인지_확인() {
        
        Lotto lotto = lottoFactory.create(NUMBERS);
        Lotto another = lottoFactory.create(NUMBERS);
        Lotto other = lottoFactory.create(NUMBERS);

        assertThat(lotto.equals(another)).isTrue();
        assertThat(another.equals(other)).isTrue();
        assertThat(lotto.equals(other)).isTrue();
    }

    @Test
    public void equals_null과_비교했을때_false반환하는지_확인() {
        
        Lotto lotto = lottoFactory.create(NUMBERS);

        assertThat(lotto.equals(null)).isFalse();
    }

    @Test
    public void 중복된_숫자_리스트가_입력됬을때_예외발생_검사() {
        List<Integer> NUMBERS = Arrays.asList(1, 2, 3, 4, 5, 5);

        assertThatExceptionOfType(NumberDuplicationException.class).isThrownBy(() -> {
            lottoFactory.create(NUMBERS);
        });
    }

    @Test
    public void 숫자_리스트의_크기가_6초과일때_예외발생_검사() {
        List<Integer> NUMBERS = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

        assertThatExceptionOfType(CountOfNumberException.class).isThrownBy(() -> {
            lottoFactory.create(NUMBERS);
        });
    }

    @Test
    public void 숫자_리스트의_크기가_6미만일때_예외발생_검사() {
        List<Integer> NUMBERS = Arrays.asList(1, 2, 3, 4, 5);

        assertThatExceptionOfType(CountOfNumberException.class).isThrownBy(() -> {
            lottoFactory.create(NUMBERS);
        });
    }

    @Test
    public void 로또_숫자가_범위를_벗어날때_예외발생하는지_검사() {
        List<Integer> NUMBERS = Arrays.asList(1, 2, 3, 4, 46, 7);

        assertThatExceptionOfType(NumberBoundException.class).isThrownBy(() -> {
            lottoFactory.create(NUMBERS);
        });
    }

    @Test
    public void 로또_숫자가_모두_같을때_확인() {
        
        List<Integer> anotherNumbers = Arrays.asList(2, 3, 4, 5, 6, 1);

        Lotto lotto = lottoFactory.create(NUMBERS);
        Lotto another = lottoFactory.create(anotherNumbers);

        assertThat(lotto.matchCount(another)).isEqualTo(6);
    }

    @Test
    public void 로또_숫자가_5개_같을때() {
        
        List<Integer> anotherNumbers = Arrays.asList(2, 3, 4, 5, 6, 7);

        Lotto lotto = lottoFactory.create(NUMBERS);
        Lotto another = lottoFactory.create(anotherNumbers);

        assertThat(lotto.matchCount(another)).isEqualTo(5);
    }

    @Test
    public void 로또_숫자가_4개_같을때() {
        
        List<Integer> anotherNumbers = Arrays.asList(2, 3, 4, 5, 8, 7);

        Lotto lotto = lottoFactory.create(NUMBERS);
        Lotto another = lottoFactory.create(anotherNumbers);

        assertThat(lotto.matchCount(another)).isEqualTo(4);
    }

    @Test
    public void 로또_숫자가_3개_같을때() {
        
        List<Integer> anotherNumbers = Arrays.asList(12, 13, 4, 5, 6, 15);

        Lotto lotto = lottoFactory.create(NUMBERS);
        Lotto another = lottoFactory.create(anotherNumbers);

        assertThat(lotto.matchCount(another)).isEqualTo(3);
    }

    @Test
    public void 로또_숫자가_2개_같을때() {
        
        List<Integer> anotherNumbers = Arrays.asList(12, 13, 4, 5, 16, 15);

        Lotto lotto = lottoFactory.create(NUMBERS);
        Lotto another = lottoFactory.create(anotherNumbers);

        assertThat(lotto.matchCount(another)).isEqualTo(2);
    }

    @Test
    public void 로또_숫자가_1개_같을때() {
        
        List<Integer> anotherNumbers = Arrays.asList(12, 23, 4, 14, 25, 15);

        Lotto lotto = lottoFactory.create(NUMBERS);
        Lotto another = lottoFactory.create(anotherNumbers);

        assertThat(lotto.matchCount(another)).isEqualTo(1);
    }

    @Test
    public void 로또_숫자가_하나도_같은게_없을때() {
        
        List<Integer> anotherNumbers = Arrays.asList(12, 23, 14, 25, 16, 15);

        Lotto lotto = lottoFactory.create(NUMBERS);
        Lotto another = lottoFactory.create(anotherNumbers);

        assertThat(lotto.matchCount(another)).isEqualTo(0);
    }
}