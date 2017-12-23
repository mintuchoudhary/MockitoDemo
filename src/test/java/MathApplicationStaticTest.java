import demo.MathApplicationStatic;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.whenNew;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.api.mockito.PowerMockito;

import java.io.IOException;

/**
 * Created by mich0916 on 1/31/2017.
 */
@RunWith(PowerMockRunner.class)  //MockitoJUnitRunner will not work
@PrepareForTest(MathApplicationStatic.class)
public class MathApplicationStaticTest {
  @Test
  public void testClassWithStaticMethod()
  {
      // mock all the static methods in a class called "Static"
      PowerMockito.mockStatic(MathApplicationStatic.class);
      // use Mockito to set up your expectation
      when(MathApplicationStatic.add(10,30)).thenReturn(40);


      Assert.assertNotNull(MathApplicationStatic.add(10,30));
      Assert.assertEquals(MathApplicationStatic.add(10,30),40);

  }
}
