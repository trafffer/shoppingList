package com.ex.prep.services.impls;

import com.ex.prep.model.binding.UserLoginBindingModel;
import com.ex.prep.model.binding.UserRegisterBindingModel;
import com.ex.prep.model.entity.User;
import com.ex.prep.model.service.UserServiceModel;
import com.ex.prep.repositories.UserRepository;
import com.ex.prep.security.CurrentUser;
import com.ex.prep.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.currentUser = currentUser;
    }


    @Override
    public void registerUser(UserRegisterBindingModel userModel) {
        UserServiceModel userSModel =
                findUserByUsernameAndPassword(userModel.getUsername(),userModel.getPassword());
        if (userSModel==null){
            userSModel=mapper.map(userModel,UserServiceModel.class);
            User user = mapper.map(userSModel,User.class);
            userRepository.save(user);
        }
    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .map(user->mapper.map(user,UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void login(UserServiceModel userServiceModel) {
           currentUser.setUsername(userServiceModel.getUsername()).setId(userServiceModel.getId());
    }

    @Override
    public void logout() {
        this.currentUser.setUsername(null).setId(null);
    }
}
