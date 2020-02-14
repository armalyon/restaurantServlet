package ua.restaurant.srvlt.model.entity;

import ua.restaurant.srvlt.model.entity.type.Role;

import java.time.LocalDateTime;

public class User {
    private long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private Role role;
    private LocalDateTime registrationDate;
    private long funds;

    public User(long id, String username, String password, String name, String surname, Role role, LocalDateTime registrationDate, long funds) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.registrationDate = registrationDate;
        this.funds = funds;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public long getFunds() {
        return funds;
    }

    public void setFunds(long funds) {
        this.funds = funds;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role=" + role +
                ", registrationDate=" + registrationDate +
                ", funds=" + funds +
                '}';
    }

    public static class Builder {
        private User newUser;

        public Builder(){
            newUser = new User();
        }

        public Builder id(Long id){
            newUser.id = id;
            return this;
        }

        public Builder username(String username){
            newUser.username = username;
            return this;
        }

        public Builder password(String password){
            newUser.password = password;
            return this;
        }

        public Builder name(String name){
            newUser.name = name;
            return this;
        }

        public Builder surname(String surname){
            newUser.surname = surname;
            return this;
        }

        public Builder role(Role role){
            newUser.role = role;
            return this;
        }

        public Builder registrationDate(LocalDateTime date){
            newUser.registrationDate = date;
            return this;
        }

        public Builder funds(long funds){
            newUser.funds = funds;
            return this;
        }

        public User build(){
            return newUser;
        }

    }
}
