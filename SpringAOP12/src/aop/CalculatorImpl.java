package aop;

import org.springframework.stereotype.Component;

@Component("target")
public class CalculatorImpl implements Calculator {

	@Override
	public int add(int x, int y) {
	
		int result = x + y; // 주업무

		return result;
	}

	@Override
	public int sub(int x, int y) {

		int result = x - y; // 주업무

		return result;
	}

	@Override
	public int mul(int x, int y) {

		int result = x * y; // 주업무

		return result;
	}

	@Override
	public int div(int x, int y) {

		int result = x / y; // 주업무

		return result;
	}

}
