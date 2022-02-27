package study;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ShuffleTest {
	List<Object> intList;

	@BeforeEach
	void initializeList() {
		intList = Arrays.asList(1, 2, 3, 4, 5, 6);
		Collections.shuffle(intList);
	}

	@DisplayName("셔플된 리스트의 원소가 변경되지 않는다")
	@Test
	void shuffle_numbers_contains() {
		assertThat(intList).contains(1, 2, 3, 4, 5, 6);
	}

	@DisplayName("셔플된 리스트의 원소의 순서가 변경된다")
	@Test
	void shuffle_numbers_sequence() {
		assertThat(intList).doesNotContainSequence(5, 1, 2, 6, 4, 3);
	}
}
