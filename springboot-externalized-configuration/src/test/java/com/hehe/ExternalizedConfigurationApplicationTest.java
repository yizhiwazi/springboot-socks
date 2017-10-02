package com.hehe;

import com.hehe.config.MyDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExternalizedConfigurationApplicationTest {

	@Autowired
	MyDataSource myDataSource;

	@Test
	public void testconfig() {
	  MyDataSource dataSource = new MyDataSource();
	  dataSource.setUrl("jdbc:mysql://localhost:3306/socks?useSSL=false");
	  dataSource.setUsername("root");
	  dataSource.setPassword("root");
	  dataSource.setDriverClassName("com.mysql.jdbc.Driver");
      assertThat(myDataSource).isEqualTo(dataSource);
	}

}
