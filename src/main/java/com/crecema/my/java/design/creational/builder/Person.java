package com.crecema.my.java.design.creational.builder;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {

    private Integer id;
    private String name;
    private Integer age;
    private Integer sex;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Integer id;
        private String name;
        private Integer age;
        private Integer sex;

        Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder sex(Integer sex) {
            this.sex = sex;
            return this;
        }

        public Person build() {
            return new Person(this.id, this.name, this.age, this.sex);
        }

    }

    public static void main(String[] args) {
        Person person = Person.builder()
                .age(24)
                .id(1001)
                .name("Haoran Ma")
                .build();
        System.out.println(person);
    }

}
