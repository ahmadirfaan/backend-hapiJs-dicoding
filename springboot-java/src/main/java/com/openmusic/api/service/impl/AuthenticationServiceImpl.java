
package com.openmusic.api.service.impl;

import com.openmusic.api.entities.database.Authentication;
import com.openmusic.api.exception.EntityNotFoundException;
import com.openmusic.api.repository.jpa.AuthenticationRepository;
import com.openmusic.api.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ahmad Irfaan Hibatullah
 * @version $Id: AuthenticationServiceImpl.java, v 0.1 2021‐10‐20 01.24 Ahmad Irfaan Hibatullah Exp $$
 */

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    protected final AuthenticationRepository repository;

    @Autowired
    public AuthenticationServiceImpl(AuthenticationRepository repository) {
        this.repository = repository;
    }

    @Override
    public Authentication addRefreshToken(String token) {
        Authentication entity = new Authentication();
        entity.setToken(token);
        return repository.save(entity);
    }

    @Override
    public Authentication verifyRefreshToken(String token) {
        Authentication entity = repository.findByToken(token);
        if (entity.getDeletedDate() != null || entity == null) {
            throw new EntityNotFoundException();
        } else {
            return entity;
        }
    }

    @Override
    public Authentication deleteRefreshToken(String token) {
        Authentication entity = repository.findByToken(token);
        if (entity != null && entity.getDeletedDate() == null) {
            return entity;
        } else {
            throw new EntityNotFoundException();
        }
    }
}