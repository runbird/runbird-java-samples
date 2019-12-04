package com.scy.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

/**
 * 描述：类名可以随意 ，字段需要保持一致 <br>
 */
@Data
public class User {

    private String id;

    @NotBlank
    private String name;

    @Range(min = 0,max = 100)
    private int age;
}
