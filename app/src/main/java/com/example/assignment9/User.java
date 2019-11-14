package com.example.assignment9;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    private String username;
    private String password;
    // private String timestamp;
    // private String loginInfo;
    @PrimaryKey(autoGenerate = true)
    private Integer id;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        // this.timestamp = timestamp;
        // this.loginInfo = loginInfo;
    }

    public void setId(Integer id) {this.id = id;}

    public String getUsername() {return username;}

    public String getPassword() {return password;}

    // public String getTimestamp() {return timestamp;}

    public Integer getId(){return id;}

    // public String getLoginInfo() {return loginInfo;}
    // public void setLoginInfo() {this.loginInfo = loginInfo;}

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
