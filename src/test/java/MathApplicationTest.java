/**
 * Created by mich0916 on 1/24/2017.
 */

import demo.CalculatorService;
import demo.MathApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


// @RunWith attaches a runner with the test class to initialize the test data
@RunWith(MockitoJUnitRunner.class)
public class MathApplicationTest {

    //@Mock annotation is used to create the mock object to be injected
    @Mock
    CalculatorService calculatorService;

    //@InjectMocks annotation is used to create and inject the mock object
    @InjectMocks
    MathApplication service;

    @Test
    public void testAdd() {
        //add the behavior of calc service to add two numbers
        when(calculatorService.add(10, 20)).thenReturn(30);

        //test the add functionality

        Assert.assertEquals(service.add(10, 20), 30);

        //verify call to calcService is made or not with same arguments.
        verify(calculatorService).add(10, 20);

        //limit the method call to 1, no less and no more calls are allowed
        verify(calculatorService, times(1)).add(10, 20);

        //verify that method was never called on a mock
        verify(calculatorService, never()).multiply(10, 20);

        //check a minimum 1 call count with same arguments
        verify(calculatorService, atLeastOnce()).add(10, 20);

        //check if add function is called minimum 2 times
        //verify(calculatorService, atLeast(2)).add(10, 20);

        //check if add function is called maximum 3 times  , other way when we try with 0 the test should fail
        verify(calculatorService, atMost(3)).add(10, 20);
    }

    @Test(expected = RuntimeException.class)
    public void testAddToThrowException() {
        //add the behavior to throw exception when add method is invoked
        when(calculatorService.add(20, 20)).thenThrow(new RuntimeException());
        //OR
        //doThrow(new RuntimeException()).when(calculatorService.add(20,30));
    }

    @Test
    public void testAddSubInorderAndReset() {
        when(calculatorService.add(40, 20)).thenReturn(60);
        when(calculatorService.subtract(40, 20)).thenReturn(20);


        //create an inOrder verifier for a single mock
        InOrder inOrderObject = inOrder(calculatorService);

        Assert.assertEquals(service.subtract(40, 20), 20);
        Assert.assertEquals(service.add(40, 20), 60);

        inOrderObject.verify(calculatorService).subtract(40, 20);
        inOrderObject.verify(calculatorService).add(40, 20);


        //reset mock
        /*reset(calculatorService);
        Assert.assertEquals(service.add(40,20),60);*/


    }


    @Test
    public void testSpy() {
        List list = new ArrayList<Integer>(10);
        list.add(10);
        list.add(20);
        //create a spy on actual object
        list = spy(list);

        when(list.get(0)).thenReturn(20);
        when(calculatorService.add(20, 20)).thenReturn(40);

        Assert.assertEquals(service.add((Integer) list.get(0), (Integer) list.get(1)), 40);

    }

    @Test
    public void testSpy2() {
        CalculatorServiceImpl calculatorServiceImpl = new CalculatorServiceImpl();
        //create a spy on actual objects
        calculatorServiceImpl = spy(calculatorServiceImpl);
        service.setCalculatorService(calculatorServiceImpl);
        Assert.assertEquals(service.add(20, 50), 70);

    }

    //Behaviour Driven Development
    @Test
    public void testGivenWhenThen() {
        //Given
        given(calculatorService.add(20, 10)).willReturn(30);

        //when
        int result = calculatorService.add(20, 10);

        //then
        Assert.assertSame(result, 30);
    }

    @Test
    public void testTimeout() {
        when(calculatorService.add(40, 20)).thenReturn(60);
        Assert.assertEquals(service.add(40, 20), 60);

        //verify call of method to be completed within 100 ms
        verify(calculatorService, timeout(100)).add(40, 20);
    }

    @Test
    public void testDoNothing() {
        doNothing().when(calculatorService).display();

        calculatorService.display();
    }

    class CalculatorServiceImpl implements CalculatorService {

        public int add(int input1, int input2) {

            return input1 + input2;
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

        public void display() {
            System.out.println("Display method");
        }
    }
}
