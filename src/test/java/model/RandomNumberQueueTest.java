package model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RandomNumberQueueTest {

    @Test
    @DisplayName("랜덤 값 생성하기")
    void createRandomNumbers() {
        NumberQueue randomNumberQueue = new RandomNumberQueue(1, 1000);
        Set<Integer> randomNumbers = new HashSet<>();

        while (randomNumberQueue.hasNext()) {
            int randomNumber = randomNumberQueue.get();
            if (randomNumbers.contains(randomNumber)) {
                fail("이미 있는 원소");
            }
            randomNumbers.add(randomNumber);
        }

        assertThat(randomNumbers).hasSize(1000);
    }
}
