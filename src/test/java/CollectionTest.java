import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CollectionTest {

    @Test
    @DisplayName("unmodifiableList 테스트")
    void collectionUnmodifiableList() {
        List<String> strs = new ArrayList<>();
        strs.add("a");
        strs.add("b");
        strs.add("c");
        List<String> copy = Collections.unmodifiableList(strs);
        strs.add("d");
        assertThat(strs).isEqualTo(copy);
        assertThatThrownBy(() -> copy.add("e"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("copyOf 테스트")
    void collectionCopyOf() {
        List<String> strs = new ArrayList<>();
        strs.add("a");
        strs.add("b");
        strs.add("c");
        List<String> copy = List.copyOf(strs);
        strs.add("d");
        assertThat(strs).isNotEqualTo(copy);
        assertThatThrownBy(() -> copy.add("e"))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    @DisplayName("깊은 복사")
    void deepCopy() {
        List<Car> cars1 = List.of(new Car("alex"), new Car("kei"),
                new Car("cris"), new Car("roma"));

        List<Car> cars2 = new ArrayList<>();
        cars1.forEach(car -> cars2.add(new Car(car.getName())));
        cars2.get(0).setName("새 이름");

        assertThat(cars1).isNotEqualTo(cars2);
    }

    @Test
    @DisplayName("방어적 복사")
    void defensiveCopy() {
        List<Car> cars1 = List.of(new Car("alex"), new Car("kei"),
                new Car("cris"), new Car("roma"));

        List<Car> cars2 = new ArrayList<>(cars1);
        cars2.get(0).setName("새 이름");
        assertThat(cars1).isEqualTo(cars2);
    }

    class Car {
        private String name;

        public Car(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Car)) {
                return false;
            }
            Car car = (Car) o;
            return Objects.equals(name, car.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
