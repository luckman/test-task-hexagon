package com.luckman.hexagon.testtask.api.client;

import com.luckman.hexagon.testtask.api.dto.GetAllUsersResult;
import com.luckman.hexagon.testtask.api.dto.User;

/**
 * In case if there are similar responses for other entities, we can quickly create new clients.
 */
public class UserClient extends BaseClient<User, GetAllUsersResult> {
    public UserClient() {
        super("/users", User.class, GetAllUsersResult.class);
    }
}
