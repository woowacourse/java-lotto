package study.stream;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IntermediateOperator {

	@DisplayName("filter를 사용하면 조건에 맞는 스트림을 반환한다")
	@Test
	void filter_test() {
		List<String> expectedList = List.of("jeong", "pro");

		List<String> list = Arrays.asList("jeong", "pro", "jdk", "java");
		List<String> filteredList = list.stream()
			.filter(x -> x.contains("o"))
			.collect(Collectors.toList());

		assertThat(filteredList).isEqualTo(expectedList);
	}

	@DisplayName("map 사용하면 조건에 맞는 스트림을 반환한다")
	@Test
	void map_test() {
		List<String> expectedList = List.of("ax", "bx");

		List<String> list = Arrays.asList("a", "b");
		List<String> mappedList = list.stream()
			.map(string -> string.concat("x"))
			.collect(Collectors.toList());

		assertThat(mappedList).isEqualTo(expectedList);
	}

	@DisplayName("flatmap 사용하면 중첩 구조를 제거한 스트림을 반환한다")
	@Test
	void flatMap_test() {
		List<String> expectedList = List.of("a", "b", "c", "d");

		List<String[]> list = Arrays.asList(new String[] {"a", "b"}, new String[] {"c", "d"});
		List<String> mappedList = list.stream()
			.flatMap(Arrays::stream)
			.collect(Collectors.toList());

		assertThat(mappedList).isEqualTo(expectedList);
	}

	@DisplayName("skip을 사용하면 그 수 만큼 건너뛴다")
	@Test
	void skip_test() {
		List<String> expectedList = List.of("cx", "dx");

		List<String> list = Arrays.asList("a", "b", "c", "d");
		List<String> mappedList = list.stream()
			.skip(2)
			.map(string -> string.concat("x"))
			.collect(Collectors.toList());

		assertThat(mappedList).isEqualTo(expectedList);
	}

	@DisplayName("peek을 사용하면 해당 요소를 소모해 명시된 동작을 수행한다")
	@Test
	void peek_test() {
		List<String> expectedList = List.of("a", "b", "c", "d");

		List<String> list = Arrays.asList("a", "b", "c", "d");
		List<String> peekedList = list.stream()
			.peek(new ArrayList<String>()::add)
			.collect(Collectors.toList());

		assertThat(peekedList).isEqualTo(expectedList);
	}

	@DisplayName("sorted를 사용하면 정렬을 해준다")
	@Test
	void sorted_test() {
		List<String> expectedList = List.of("a", "b", "c", "d");

		List<String> list = Arrays.asList("d", "c", "b", "a");
		List<String> sortedList = list.stream()
			.sorted()
			.collect(Collectors.toList());

		assertThat(sortedList).isEqualTo(expectedList);
	}

	@DisplayName("sorted를 사용하면 정렬을 해준다")
	@Test
	void sorted_reverse_test() {
		List<String> expectedList = List.of("d", "c", "b", "a");

		List<String> list = Arrays.asList("a", "b", "c", "d");
		List<String> sortedList = list.stream()
			.sorted(Comparator.reverseOrder())
			.collect(Collectors.toList());

		assertThat(sortedList).isEqualTo(expectedList);
	}

	@DisplayName("distinct를 사용하면 중복이 제거된다")
	@Test
	void distinct_test() {
		List<String> expectedList = List.of("a", "b", "c", "d");

		List<String> list = Arrays.asList("a", "a", "b", "c", "c", "d");
		List<String> distinctList = list.stream()
			.distinct()
			.collect(Collectors.toList());

		assertThat(distinctList).isEqualTo(expectedList);
	}

	@DisplayName("limit를 사용하면 앞의 요소까지 새로운 Stream을 만든다")
	@Test
	void limit_test() {
		List<String> expectedList = List.of("a", "b");

		List<String> list = Arrays.asList("a", "b", "c", "d");
		List<String> limitedList = list.stream()
			.limit(2)
			.collect(Collectors.toList());

		assertThat(limitedList).isEqualTo(expectedList);
	}

	@DisplayName("concat을 사용하면 두 스트림을 이어준다")
	@Test
	void concat_test() {
		List<String> expectedList = List.of("a", "b", "c", "d");

		List<String> list1 = Arrays.asList("a", "b");
		List<String> list2 = Arrays.asList("c", "d");
		List<String> concatList = Stream.concat(list1.stream(), list2.stream())
			.collect(Collectors.toList());

		assertThat(concatList).isEqualTo(expectedList);
	}
}
