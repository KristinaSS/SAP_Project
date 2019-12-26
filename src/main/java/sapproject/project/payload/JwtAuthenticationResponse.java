package sapproject.project.payload;

public class JwtAuthenticationResponse {
    private String token;
   // private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken) {
        this.token = accessToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

/*    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }*/
}

