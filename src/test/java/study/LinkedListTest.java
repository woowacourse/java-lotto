package study;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class LinkedListTest {
    @Test
    void linkedListStudy() {
        List<String> strings = new LinkedList<>();
        strings.add("first");
        strings.add("second");

        assertAll(
                () -> assertThat(strings.add("third")).isTrue(), // 세 번째 값을 추가하라.
                () -> assertThat(strings.size()).isEqualTo(3), // list의 크기를 구하라.
                () -> assertThat(strings.get(0)).isEqualTo("first"), // 첫 번째 값을 찾아라.
                () -> assertThat(strings.contains("first")).isTrue(), // "first" 값이 포함되어 있는지를 확인해라.
                () -> assertThat(strings.remove(0)).isEqualTo("first"), // 첫 번째 값을 삭제해라.
                () -> assertThat(strings.size()).isEqualTo(2) // 값이 삭제 됐는지 확인한다.
        );

        // TODO values에 담긴 모든 값을 출력한다.
        strings.forEach(System.out::println);
    }
}
