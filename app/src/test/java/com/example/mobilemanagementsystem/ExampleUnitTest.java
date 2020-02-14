package com.example.mobilemanagementsystem;

import com.example.mobilemanagementsystem.bll.LoginBLL;
import com.example.mobilemanagementsystem.bll.OrderBLL;
import com.example.mobilemanagementsystem.bll.SignupBLL;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test

    public void testlogin() {
        LoginBLL loginBLL = new LoginBLL();
        boolean result = loginBLL.checkLogin("abc", "abc");
        assertEquals(true, result);
    }

    @Test
    public void testloginFail() {
        LoginBLL loginBLL = new LoginBLL();
        boolean result = loginBLL.checkLogin("abc", "abcd");
        assertEquals(false, result);
    }

    @Test
    public void signup() {
        SignupBLL signupBLL = new SignupBLL();
        boolean result = signupBLL.signupcheck("ritesh", "9878787767", "ritesh@gmail.com", "ritz", "123", "rit.png");
        assertEquals(true, result);
    }


    @Test
    public void signupFail() {
        SignupBLL signupBLL = new SignupBLL();
        boolean result = signupBLL.signupcheck("ritesh", "9878787767", "ritesh@gmail.com", "ritz", "123", "rit.png");
        assertEquals(false, result);
    }

    @Test

    public void orderTet() {
        OrderBLL orderBLL = new OrderBLL();
        boolean result = orderBLL.order("232323#2338787", "4");
        assertEquals(true, result);
    }






}