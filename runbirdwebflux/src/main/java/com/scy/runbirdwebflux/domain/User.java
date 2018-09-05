package com.scy.runbirdwebflux.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    private String name;

    private int age;
}
