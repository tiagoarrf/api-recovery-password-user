# api-recovery-password-user

This repository provides an example of password reset using a 6-digit code sent to the valid email address of the user registered in the database. The logic for SMS implementation is also the same.

![image](https://user-images.githubusercontent.com/38273600/180907497-4c7f94aa-be92-4900-8aca-3c2c83dc956a.png)


## Endpoints and examples:

- POST (add user):
##### url: http://localhost:8080/person/add

##### JSON used:
```json
{
	"name": "Name User",
	"email": "email-valid-user",
	"password": "exmple123",
	"role": "ADMIN"
}
```

- GET (get user by email):
##### url: http://localhost:8080/person/{email-user}

##### JSON retorned:
```http
{
    "id": 1,
    "name": "Name User",
    "email": "example@mail.example",
    "password": "$2a$12$ILwnkNZ/3eKXGVqbbRYZiOXLDH.vvfe1vTGLhtTMN93s/Rw5MjOQW",
    "role": "ADMIN",
    "enabled": true,
    "username": "example@mail.example",
    "authorities": [
        "ADMIN"
    ],
    "accountNonLocked": true,
    "credentialsNonExpired": true,
    "accountNonExpired": true
}
```

- POST (request OTP code for reset password by email user):
##### url: http://localhost:8080/otp/request-new-password/?email={email-user}

Com isso, um código de 6 dígitos com data de validade personalizável será enviado para o e-mail válido fornecido pelo usuário.


- POST (reset password by code of 6 digits):
##### url: http://localhost:8080/person/reset-password/?otp={otp-code}&newPassword={new-password}

If the code is valid, the request will be accepted and the new password will be in use.



