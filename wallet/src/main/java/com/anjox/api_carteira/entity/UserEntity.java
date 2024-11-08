package com.anjox.api_carteira.entity;

import com.anjox.api_carteira.enums.UserEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private UserEnum userType;

    private String token;

    private Boolean enabled;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id", referencedColumnName = "id")
    private WalletEntity wallet;


    public UserEntity() {

    }

    public UserEntity(Long id, String username, String email, String password, UserEnum userType, String token, Boolean enabled, WalletEntity wallet) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.token = token;
        this.enabled = enabled;
        this.wallet = wallet;
    }

    public UserEntity(String username, String email, String password, UserEnum userType, String token, Boolean enabled, WalletEntity wallet) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.token = token;
        this.enabled = enabled;
        this.wallet = wallet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public UserEnum getUserType() {
        return userType;
    }

    public void setUserType(UserEnum userType) {
        this.userType = userType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public WalletEntity getWallet() {
        return wallet;
    }

    public void setWallet(WalletEntity wallet) {

        this.wallet = wallet;
    }
}
