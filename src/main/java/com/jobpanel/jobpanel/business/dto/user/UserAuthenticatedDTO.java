package com.jobpanel.jobpanel.business.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UserAuthenticatedDTO {
    private Long id;
    private String accessToken;
    private String refreshToken;
    private String username;
    private String role;
}
