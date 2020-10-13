package com.example.backend.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    
    @Id
    @Column(name="ID")
    private long id; 

    @Column(name="FST_NM")
    private String firstName; 

    @Column(name="LST_NM")
    private String lastName;

    @Column(name="EMAIL")
    private String email;

    @Column(name="PASSWORD_NM")
    private String password; 

    @Column(name="RESET_TOKEN")
    private String resetToken; 

    @Column(name="RESET_TOKEN_EXPIRY")
    private String resetTokenExpiration; 

    @Column(name="CRTE_TM")
    private Timestamp createTime; 

    @Column(name="CRTE_BY_ACCT_KEY")
    private long createByAccountKey; 

    @Column(name="LST_UPDT_TM")
    private Timestamp lastUpdateTime; 

    @Column(name="LST_UPDT_BY_ACCT_KEY")
    private long lastUpdateByAccountKey; 

    @Column(name="ACT_IND")
    private int activeIndicator; 

    public User() {

    }
    public User(String firstName, String lastName, String email, Integer activeIndicator) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.activeIndicator = activeIndicator;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getResetTokenExpiration() {
        return resetTokenExpiration;
    }

    public void setResetTokenExpiration(String resetTokenExpiration) {
        this.resetTokenExpiration = resetTokenExpiration;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public long getCreateByAccountKey() {
        return createByAccountKey;
    }

    public void setCreateByAccountKey(long createByAccountKey) {
        this.createByAccountKey = createByAccountKey;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public long getLastUpdateByAccountKey() {
        return lastUpdateByAccountKey;
    }

    public void setLastUpdateByAccountKey(long lastUpdateByAccountKey) {
        this.lastUpdateByAccountKey = lastUpdateByAccountKey;
    }

    public int getActiveIndicator() {
        return activeIndicator;
    }

    public void setActiveIndicator(int activeIndicator) {
        this.activeIndicator = activeIndicator;
    }

    
}
