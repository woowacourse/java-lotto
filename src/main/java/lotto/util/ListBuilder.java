package lotto.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListBuilder {
	@SafeVarargs
	public static <T> List<T> merge(final List<T>... lists) {
		return Stream.of(lists)
				.map(List::stream)
				.reduce(Stream.empty(), Stream::concat)
				.collect(Collectors.toUnmodifiableList());
	}
}
