package study;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class DomainTest {

    @Test
    void toString_stillNeedsEqualsAndHashCodeForEquality() {
        NameClass object1 = new NameClass("same name");
        NameClass object2 = new NameClass("same name");

        assertThat(object1).isNotEqualTo(object2);
    }

    private static class NameClass {
        private final String name;

        public NameClass(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "NameClass{" + "name='" + name + '\'' + '}';
        }
    }
}
