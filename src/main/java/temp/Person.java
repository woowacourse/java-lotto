package temp;

public class Person {
    private String name;
    private int age;
    private String address;
    private String gender;

    public static final class PersonBuilder {
        //필수 매개변수
        private final String name;
        private final int age;

        //선택 매개변수
        private String address = "";
        private String gender = "";

        private PersonBuilder(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public static PersonBuilder aPerson(String name, int age) {
            return new PersonBuilder(name, age);
        }

        public PersonBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public PersonBuilder withGender(String gender) {
            this.gender = gender;
            return this;
        }

        public Person build() {
            Person person = new Person();
            person.name = this.name;
            person.age = this.age;
            person.address = this.address;
            person.gender = this.gender;
            return person;
        }
    }
}
