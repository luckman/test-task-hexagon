package com.luckman.hexagon.testtask.api;

import com.luckman.hexagon.testtask.api.client.UserClient;
import com.luckman.hexagon.testtask.api.dto.GetAllUsersResult;
import com.luckman.hexagon.testtask.api.dto.User;
import com.luckman.hexagon.testtask.api.utils.Utils;
import okhttp3.Response;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class ApiTest {
    private UserClient userClient = new UserClient();       // can also be singleton

    @Test
    public void getAllUsersTest() {
        Response response = userClient.getAllAsResponse();
        assertEquals(200, response.code(), "Status code is not correct");
        GetAllUsersResult getAllUsersResult = Utils.extractFromResponse(response, GetAllUsersResult.class);
        assertEquals(12, getAllUsersResult.getTotal(), "Wrong total number of users");
    }

    @Test
    public void testAllUsersUnique() {
        GetAllUsersResult result = userClient.getAll();
        int totalPages = result.getTotalPages();
        int totalUsers = result.getTotal();
        int usersPerPage = result.getPerPage();

        assertEquals(2, totalPages, "Wrong number of total pages"); // in sample there are exactly 12 users
        Set<Long> ids = new HashSet<>();
        Set<String> emails = new HashSet<>();
        for (int page = 1; page <= totalPages; page++) {
            // last page may contain fewer users. For 12 users numbers would be 6 and 6
            int expectedUsersOnPage = (page < totalPages) ? usersPerPage : totalUsers - (usersPerPage * (totalPages - 1));

            List<User> usersOnPage = userClient.getPage(page).getData();
            Set<Long> idsOnPage = usersOnPage.stream()
                    .map(User::getId)
                    .collect(Collectors.toSet());
            Set<String> emailsOnPage = usersOnPage.stream()
                    .map(User::getEmail)
                    .collect(Collectors.toSet());

            assertEquals(expectedUsersOnPage, usersOnPage.size(),
                    "Number of users on page " + page + " is incorrect");
            assertEquals(expectedUsersOnPage, idsOnPage.size(),
                    "Amount of unique ids on page " + page + " is incorrect");
            assertEquals(expectedUsersOnPage, emailsOnPage.size(),
                    "Amount of unique emails on page " + page + " is incorrect");

            ids.addAll(idsOnPage);
            emails.addAll(emailsOnPage);
        }
        assertEquals(totalUsers, ids.size(), "Users across all pages are not unique");
        assertEquals(totalUsers, emails.size(), "Emails across all pages are not unique");
    }

    @Test
    public void testEmptyPage() {
        GetAllUsersResult result = userClient.getAll();
        int totalPages = result.getTotalPages();
        GetAllUsersResult on3rdPage = userClient.getPage(totalPages + 1);
        assertEquals(0, on3rdPage.getData().size(), "Number of users on 3rd page");
    }
}
