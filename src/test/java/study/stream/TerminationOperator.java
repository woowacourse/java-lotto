package study.stream;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TerminationOperator {

	@DisplayName("count를 사용하면 개수를 세어준다")
	@Test
	void count_test() {
		long count = IntStream.of(1, 2, 3, 4, 5).count();

		assertThat(count).isEqualTo(5);
	}

	@DisplayName("sum을 사용하면 합을 구해준다")
	@Test
	void sum_test() {
		long sum = IntStream.of(1, 2, 3, 4, 5).sum();

		assertThat(sum).isEqualTo(15);
	}

	@DisplayName("min을 사용하면 최소를 구해준다")
	@Test
	void min_test() {
		OptionalInt min = IntStream.of(1, 2, 3, 4, 5).min();

		assertThat(min.getAsInt()).isEqualTo(1);
	}

	@DisplayName("max을 사용하면 최소를 구해준다")
	@Test
	void max_test() {
		OptionalInt max = IntStream.of(1, 2, 3, 4, 5).max();

		assertThat(max.getAsInt()).isEqualTo(5);
	}

	@DisplayName("average를 사용하면 평균을 구해준다")
	@Test
	void average_test() {
		OptionalDouble average = IntStream.of(1, 2, 3, 4, 5).average();

		assertThat(average.getAsDouble()).isEqualTo(3);
	}

	@DisplayName("reduce를 사용하면 누적된 값을 계산한다")
	@Test
	void reduce_test() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

		int result = list.stream()
			.reduce(0, Integer::sum);

		assertThat(result).isEqualTo(15);
	}

	@DisplayName("Collectors.joining을 사용하면 스트림 작업의 결과를 하나의 스트링으로 연결해준다")
	@Test
	void joining_test() {
		String expectedResult = "a1, 2, 3, 4, 5z";

		List<String> list = Arrays.asList("1", "2", "3", "4", "5");
		String joinedResult = list.stream()
			.collect(Collectors.joining(", ", "a", "z"));

		assertThat(joinedResult).isEqualTo(expectedResult);
	}

	@DisplayName("Collectors.groupingBy를 사용하면 특정 조건으로 요소들을 그룹화하여 Map 타입으로 반환한다")
	@Test
	void grouping_test() {
		Map<Integer, List<String>> expectedMap = new HashMap<>();
		expectedMap.put(1, Arrays.asList("a", "c"));
		expectedMap.put(2, Arrays.asList("bb", "ee"));
		expectedMap.put(3, List.of("ddd"));

		List<String> list = Arrays.asList("a", "bb", "c", "ddd", "ee");
		Map<Integer, List<String>> groupedMap = list.stream()
			.collect(Collectors.groupingBy(String::length, Collectors.toList()));

		assertThat(groupedMap).isEqualTo(expectedMap);
	}

	@DisplayName("Collectors.partitioningBy 사용하면 특정 조건으로 요소들을 그룹화하여 Map 타입으로 반환한다")
	@Test
	void partitioning_test() {
		Map<Boolean, List<String>> expectedMap = new HashMap<>();
		expectedMap.put(false, Arrays.asList("a", "bb", "c", "ee"));
		expectedMap.put(true, List.of("ddd"));

		List<String> list = Arrays.asList("a", "bb", "c", "ddd", "ee");
		Map<Boolean, List<String>> partitionedMap = list.stream()
			.collect(Collectors.partitioningBy(string -> string.length() > 2));

		assertThat(partitionedMap).isEqualTo(expectedMap);
	}

	@DisplayName("anyMatch를 사용하면 하나라도 조건을 만족하는 요소가 있는지 확인한다")
	@Test
	void anyMatch_test() {

		List<String> list = Arrays.asList("a", "aab", "aac", "aad");
		boolean anyMatch_aa = list.stream()
			.anyMatch(string -> string.contains("aa"));

		assertThat(anyMatch_aa).isEqualTo(true);
	}

	@DisplayName("allMatch를 사용하면 하나라도 조건을 만족하는 요소가 있는지 확인한다")
	@Test
	void allMatch_test() {

		List<String> list = Arrays.asList("a", "aab", "aac", "aad");
		boolean allMatch_aa = list.stream()
			.allMatch(string -> string.contains("aa"));

		assertThat(allMatch_aa).isEqualTo(false);
	}

	@DisplayName("noneMatch를 사용하면 하나라도 조건을 만족하는 요소가 있는지 확인한다")
	@Test
	void noneMatch_test() {

		List<String> list = Arrays.asList("a", "aab", "aac", "aad");
		boolean noneMatch_e = list.stream()
			.noneMatch(string -> string.contains("e"));

		assertThat(noneMatch_e).isEqualTo(true);
	}

}
