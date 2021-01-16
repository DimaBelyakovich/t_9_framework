package com.epam.test;

import com.epam.model.User;
import com.epam.page.LoginPage;
import com.epam.service.UserCreator;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class AuthorizationTest extends CommonConditions{
    @Test
    public void authorizationTest(){
        User testUser = UserCreator.withCredentialsFromProperty();

        String loggedInUser = new LoginPage(driver)
                                .openPage()
                                .sendUserData(testUser)
                                .getLoggedUserName();
        assertThat(loggedInUser, is(equalTo(testUser.getUsername())));
    }
}
