package ua.restaurant.srvlt.dto;


import java.time.LocalDateTime;
import java.util.Objects;

public class UserInfoDTO {
    private String username;
    private String name;
    private String surname;
    private LocalDateTime registrationDate;
    private int ordersTotalNumber;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getOrdersTotalNumber() {
        return ordersTotalNumber;
    }

    public void setOrdersTotalNumber(int ordersTotalNumber) {
        this.ordersTotalNumber = ordersTotalNumber;
    }

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", registrationDate=" + registrationDate +
                ", ordersTotalNumber=" + ordersTotalNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfoDTO)) return false;
        UserInfoDTO that = (UserInfoDTO) o;
        return ordersTotalNumber == that.ordersTotalNumber &&
                username.equals(that.username) &&
                name.equals(that.name) &&
                surname.equals(that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, name, surname, ordersTotalNumber);
    }

    public static class Builder {
        private UserInfoDTO newDto;

        public Builder() {
            newDto = new UserInfoDTO();
        }

        public Builder username(String username) {
            newDto.username = username;
            return this;
        }

        public Builder name(String name) {
            newDto.name = name;
            return this;
        }

        public Builder surname(String surname) {
            newDto.surname = surname;
            return this;
        }

        public Builder registrationDate(LocalDateTime registrationDate) {
            newDto.registrationDate = registrationDate;
            return this;
        }

        public Builder totalOrders(int totalOrders) {
            newDto.ordersTotalNumber = totalOrders;
            return this;
        }

        public UserInfoDTO build() {
            return newDto;
        }
    }
}
