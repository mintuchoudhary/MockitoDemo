package demo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;

import static org.mockito.Mockito.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;


//@RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class MathApplicationTest {
	
//@InjectMocks annotation is used to create and inject the mock object
@InjectMocks 
MathApplication service;

//@Mock annotation is used to create the mock object to be injected
@Mock
CalculatorService calcService;

/*@Before
void setUp()
{

}*/
@Test
public void testAdd(){
   //add the behavior of calc service to add two numbers
   when(calcService.add(10,20)).thenReturn(30);
		
   //test the add functionality
   Assert.assertEquals(service.add(10, 20),30);
}
@Test
public void callbackTest()
{
	//add the behavior to add numbers
	 
	
	when(service.add(20,10)).thenAnswer(new Answer<Integer>() {
	   public Integer answer(InvocationOnMock invocation) throws Throwable {
	      //get the arguments passed to mock
	      Object[] args = invocation.getArguments();
	      System.out.println(args);
	      
	      //get the mock 
	      Object mock = invocation.getMock();
	      
	      System.out.println(mock);
	      //return the result
	      return 80;
	   }
	});
	Assert.assertEquals(service.add(20,10), 80);
	}
@Test 
public void testSpy()
{
	List list=new ArrayList<Integer>(10);
	list.add(10);list.add(20);
	//create a spy on actual object
	list=spy(list);
	
	when(list.get(0)).thenReturn(20);
	when(calcService.add(20,20)).thenReturn(40);
	
	
	Assert.assertEquals(service.add((Integer)list.get(0), (Integer)list.get(1)),40);
	
	}

@Test
public void testSpy2()
 {
	CalculatorServiceImpl calculatorServiceImpl = new CalculatorServiceImpl();
	calculatorServiceImpl = spy(calculatorServiceImpl);
	service.setCalculatorService(calculatorServiceImpl);
	Assert.assertEquals(service.add(20,50), 60);
	
	}
class CalculatorServiceImpl implements CalculatorService
{

	public int add(int input1, int input2) {
		
		return input1+input2;
	}

	public int subtract(int input1, int input2) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int multiply(int input1, int input2) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int divide(int input1, int input2) {
		// TODO Auto-generated method stub
		return 0;
	}
	}
}