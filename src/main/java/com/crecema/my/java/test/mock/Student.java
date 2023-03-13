package com.crecema.my.java.test.mock;

import com.crecema.my.java.collection.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private Integer id;
    private String name;
    private Integer age;
    private Integer sex;
    private Extra extra;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Extra {

        private List<String> skills;

    }
}
