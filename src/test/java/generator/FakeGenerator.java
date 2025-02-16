package generator;

import global.generator.Generator;
import java.util.List;

public class FakeGenerator implements Generator {
    @Override
    public List<Integer> generate() {
        return List.of(1,2,3,4,5,6);
    }
}
