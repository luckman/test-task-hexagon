package com.luckman.hexagon.testtask.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetAllUsersResult extends GetAllResult {
    private List<User> data;
}
