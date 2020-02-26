package study;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

public class RandomTest {

    private Random random = new Random();

    @Test
    void nextInt() {
        int result = random.nextInt(1);
        assertThat(result).isEqualTo(0);
    }
}
