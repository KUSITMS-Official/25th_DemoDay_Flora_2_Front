package com.example.flora.response;

import com.google.gson.annotations.SerializedName;
/*
{
  "grantType": "bearer",
  "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBmbG9yYS5jb20iLCJhdXRoIjoiUk9MRV9VU0VSIiwiZXhwIjoxNjUzMjExMjY5fQ.5ZMdFC7tTkdkE77mnJR66JvMwtZt7SkLHlbKap8QDbe8y8sOxpXRhCbZ0x1u1N_VCL0nUBNiL9izNDiIA7MYnw",
  "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTM4MTQyNjl9.NtYjXu0MPhQBpsAGVIvSaVoMYL5tOSeNMDsnEY_xcLZuXhuMGtt5vYGahZeisNz49OUoLsS4y1gGIkifdNimqA",
  "accessTokenExpiresIn": 1653211269398
}
 */
public class UserToken {

    @SerializedName("grantType")
    private String grantType;

    @SerializedName("accessToken")
    private String accessToken;

    @SerializedName("refreshToken")
    private String refreshToken;

    @SerializedName("accessTokenExpiresIn")
    private Long accessTokenExpiresIn;

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getAccessTokenExpiresIn() {
        return accessTokenExpiresIn;
    }

    public void setAccessTokenExpiresIn(Long accessTokenExpiresIn) {
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }

}
