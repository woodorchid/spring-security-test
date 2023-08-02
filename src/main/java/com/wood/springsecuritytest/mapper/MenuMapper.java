package com.wood.springsecuritytest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wood.springsecuritytest.entities.Menu;

import java.util.List;

/**
 * @author 韩志雄
 * @date 2023/7/23 17:32
 */
public interface MenuMapper extends BaseMapper<Menu> {
	List<String> selectPermsByUserId(Long id);
}
