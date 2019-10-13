package com.jdr.maven.feign.customer.model;

/**
 * @author zhoude
 * @date 2019/10/13 16:49
 */
public class UserDTO {

    private String account;
    private String password;

    public UserDTO(String account, String password) {
        this.account = account;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
