package ua.restaurant.srvlt.model.dto;


public class AccountDTO {
    private String username;
    private String password;
    private String passwordConfirmation;
    private String name;
    private String surname;

    private AccountDTO() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirmation='" + passwordConfirmation + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }

    public static class Builder {
        private AccountDTO newAccountDTO;

        public Builder() {
            newAccountDTO = new AccountDTO();
        }

        public Builder username(String username){
            newAccountDTO.username = username;
            return this;
        }

        public Builder name(String name){
            newAccountDTO.name = name;
            return this;
        }

        public Builder surname(String surname){
            newAccountDTO.surname = surname;
            return this;
        }

        public Builder password(String password){
            newAccountDTO.password = password;
            return this;
        }

        public Builder passwordConfirmation(String passwordConfirmation){
            newAccountDTO.passwordConfirmation = passwordConfirmation;
            return this;
        }

        public AccountDTO build(){
            return newAccountDTO;
        }

    }


}
