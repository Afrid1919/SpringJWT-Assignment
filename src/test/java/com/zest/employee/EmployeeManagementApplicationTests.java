package com.zest.employee;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class EmployeeManagementApplicationTests {

	private Calculator c = new Calculator();
	@Test
	void contextLoads() {
	}

	@Test
	void doSum(){
		//expected
		int expectedResult=10;

		//actual
		int actualResult= c.doSum(3,2,5);

		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	void doProduct(){
		//expected
		int expected = 15;
		//actual
		int actual = c.doProduct(3,5);

		assertThat(actual).isEqualTo(expected);
	}

}
