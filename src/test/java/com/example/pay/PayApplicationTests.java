package com.example.pay;


import com.alibaba.fastjson.JSON;
import com.example.pay.dao.CityDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PayApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private CityDao cityDao;

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void saveString() {
		redisTemplate.opsForValue().set("aab", "111");
		Assert.assertEquals("111", redisTemplate.opsForValue().get("aaa"));
		System.out.println(redisTemplate.opsForValue().get("aaa"));
	}

	@Test
	public void findAlltest() {

		System.out.println(JSON.toJSONString(cityDao.findAllCity()));
	}
}
