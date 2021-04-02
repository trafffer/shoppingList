package com.ex.prep.services;

import com.ex.prep.model.binding.UserRegisterBindingModel;
import com.ex.prep.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserRegisterBindingModel userModel);
    UserServiceModel findUserByUsernameAndPassword(String username, String password);
    void login(UserServiceModel userServiceModel);
    void logout();
}
