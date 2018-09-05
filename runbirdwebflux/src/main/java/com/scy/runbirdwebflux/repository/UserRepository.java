package com.scy.runbirdwebflux.repository;

import com.scy.runbirdwebflux.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 类名： UserRepository <br>
 * 描述：TODO <br>
 * 创建日期： 2018/9/5 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */

@Repository(value = "userRepository")
public interface UserRepository extends ReactiveMongoRepository<User,String> {
}
