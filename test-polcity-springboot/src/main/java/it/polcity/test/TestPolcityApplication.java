package it.polcity.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author marco.marini
 *	Classe principale di esecuzione
 */
@SpringBootApplication
@MapperScan("it.polcity.test.mapper")
public class TestPolcityApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestPolcityApplication.class, args);
	}
}
