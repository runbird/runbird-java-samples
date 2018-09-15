package com.scy.runbirdwebflux2.repository;

import com.scy.runbirdwebflux2.domain.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * 类名： UserRepository <br>
 * 描述：TODO <br>
 * 创建日期： 2018/9/16 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
@Repository(value = "userRepository")
public interface UserRepository extends ReactiveMongoRepository<User,String> {

    Flux<User> findByAgeBetween(int lower, int higer);

    @Query(value = "{'age':{'$gte':0,'$lte':120}}")
    Flux<User> olderUser();
}
