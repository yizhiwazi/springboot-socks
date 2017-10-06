package com.hehe;

import com.hehe.entity.User;
import com.hehe.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaApplicationTest {

	@Autowired
	UserRepository userRepository;

	@Test
	public void testuser() {

		//添加测试数据
		userRepository.save(new User("1111","test-data-jpa","first-pass"));
		userRepository.save(new User("9527","test-data-jpa","second-pass"));

		//开始进行测试
		assertThat(userRepository.existsById("9527")).isEqualTo(true);
		assertThat(userRepository.findByUsername("test-data-jpa").size()).isEqualTo(2);

	}

}
