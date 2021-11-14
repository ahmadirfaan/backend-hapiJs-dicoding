
package com.openmusic.api.service.impl;

import com.openmusic.api.entities.cache.UsersTemp;
import com.openmusic.api.entities.database.Users;
import com.openmusic.api.exception.ApplicationException;
import com.openmusic.api.exception.ClientException;
import com.openmusic.api.exception.EntityNotFoundException;
import com.openmusic.api.models.request.UserLoginRequest;
import com.openmusic.api.models.request.UserRequest;
import com.openmusic.api.repository.jpa.UsersRepository;
import com.openmusic.api.repository.redis.UserTempRepository;
import com.openmusic.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: UserServiceImpl.java, v 0.1 2021‐10‐20 10.27 Ahmad Irfaan Hibatullah Exp $$
 */
@Service
public class UserServiceImpl implements UserService {

    protected final UsersRepository usersRepository;
    protected final UserTempRepository userTempRepository;
    protected final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, UserTempRepository userTempRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.userTempRepository = userTempRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Users addUser(UserRequest request) {
        Users usersVerify = verifyUsername(request.getUsername());
        if(usersVerify == null) {
            Users users = new Users();
            users.setUsername(request.getUsername());
            users.setFullName(request.getFullName());
            users.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
            return usersRepository.save(users);
        } else {
            throw new ClientException("gagal menambahkan user, sudah terdapat username tersebut");
        }

    }

    @Override
    public Users verifyUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    @Override
    public Users findByUserId(String userId) {
        Users users = new Users();
        UsersTemp usersTemp = userTempRepository.findById(userId).orElse(null);
        if (usersTemp != null) {
            users.setId(usersTemp.getId());
            users.setUsername(usersTemp.getUsername());
            users.setPassword(usersTemp.getPassword());
            users.setFullName(usersTemp.getFullName());
        } else {
            users = usersRepository.findById(userId).orElse(null);
            if (users != null || users.getDeletedDate() == null) {
                usersTemp = new UsersTemp();
                usersTemp.setId(users.getId());
                usersTemp.setUsername(users.getUsername());
                usersTemp.setFullName(users.getFullName());
                usersTemp.setPassword(users.getPassword());
                userTempRepository.save(usersTemp);
            } else {
                throw new EntityNotFoundException("User tidak ditemukan");
            }
        }
        return users;
    }

    @Override
    public Boolean verifyUserCredentials(UserLoginRequest request) {
        Users users = verifyUsername(request.getUsername());
        if (users != null) {
            boolean matchPassword = bCryptPasswordEncoder.matches(request.getPassword(), users.getPassword());
            return matchPassword;
        } else {
            throw new ClientException("Username tidak ditemukan");
        }
    }
}