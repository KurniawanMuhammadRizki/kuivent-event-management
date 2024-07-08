package com.mini_project_event_management.event_management.users.service.impl;

import com.mini_project_event_management.event_management.exceptions.DataNotFoundException;
import com.mini_project_event_management.event_management.helpers.SlugifyHelper;
import com.mini_project_event_management.event_management.users.dto.RegisterUserRequestDto;
import com.mini_project_event_management.event_management.users.dto.RegisterUserResponseDto;
import com.mini_project_event_management.event_management.users.entity.Users;
import com.mini_project_event_management.event_management.users.repository.UsersRepository;
import com.mini_project_event_management.event_management.users.service.UsersService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
public class UserServiceImpl implements UsersService {
    private  final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder){
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public RegisterUserResponseDto register (RegisterUserRequestDto registerUserRequestDto){
        Users user = registerUserRequestDto.toEntity();
        var password = passwordEncoder.encode(user.getPassword());
        Instant now = Instant.now();
        String slug = SlugifyHelper.slugify(user.getFirstName() + user.getLastName() );

        user.setPassword(password);
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        user.setSlug(slug);

        var userRegistered = usersRepository.save(user);
        RegisterUserResponseDto registerUserResponseDto = new RegisterUserResponseDto();
        registerUserResponseDto.setSlug(userRegistered.getSlug());
        registerUserResponseDto.setEmail(userRegistered.getEmail());

        return registerUserResponseDto;
    }

    @Override
    public Users getUserById(Long id){
        Optional<Users> user = usersRepository.findById(id);
        if(user.isEmpty()){
            throw new DataNotFoundException("User not found");
        }
        return user.orElse(null);
    }

}
