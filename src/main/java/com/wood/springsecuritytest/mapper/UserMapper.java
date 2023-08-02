package com.wood.springsecuritytest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wood.springsecuritytest.entities.User;
import javax.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 韩志雄
 * @date 2023/7/21 17:30
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

}


