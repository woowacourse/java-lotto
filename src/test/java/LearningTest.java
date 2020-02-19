import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LearningTest {
	@Test
	void list() {
		List<Integer> list = new ArrayList<>();
		List<Integer> inConstructor = list;
		list.add(0, 1);

		System.out.println(list);
		System.out.println(inConstructor);

	}
}
