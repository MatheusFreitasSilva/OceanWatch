package org.example.entities.dtos;

public class EmailValidationResponse {
    private String email;
    private String user;
    private boolean smtp_check;
    private boolean mx_found;
    private boolean format_valid;
    public EmailValidationResponse(){}

    public EmailValidationResponse(String email, String user, boolean smtp_check, boolean mx_found, boolean format_valid) {
        this.email = email;
        this.user = user;
        this.smtp_check = smtp_check;
        this.mx_found = mx_found;
        this.format_valid = format_valid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean isSmtp_check() {
        return smtp_check;
    }

    public void setSmtp_check(boolean smtp_check) {
        this.smtp_check = smtp_check;
    }

    public boolean isMx_found() {
        return mx_found;
    }

    public void setMx_found(boolean mx_found) {
        this.mx_found = mx_found;
    }

    public boolean isFormat_valid() {
        return format_valid;
    }

    public void setFormat_valid(boolean format_valid) {
        this.format_valid = format_valid;
    }

    @Override
    public String toString() {
        return "EmailValidationResponse{" +
                "email='" + email + '\'' +
                ", user='" + user + '\'' +
                ", smtp_check='" + smtp_check + '\'' +
                ", mx_found='" + mx_found + '\'' +
                ", format_valid='" + format_valid + '\'' +
                '}';
    }
}
