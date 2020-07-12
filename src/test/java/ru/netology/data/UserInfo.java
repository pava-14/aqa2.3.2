package ru.netology.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserInfo {
    private String userName;
    private String userPassword;
    private String userStatus;
}
