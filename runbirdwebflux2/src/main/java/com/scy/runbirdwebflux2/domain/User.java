package com.scy.runbirdwebflux2.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

/**
 * 类名： User <br>
 * 描述：TODO <br>
 * 创建日期： 2018/9/5 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */

@Data
@Document(collection = "user")
public class User {

    @Id
    private String id;

    @NotBlank
    private String name;

    @Range(min = 0,max = 100)
    private int age;
}
