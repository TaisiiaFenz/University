package model.dto;

public class LoginResponseDto {
    private boolean isSuccess;
    private String token;

    public LoginResponseDto(boolean isSuccess, String token) {
        this.isSuccess = isSuccess;
        this.token = token;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
