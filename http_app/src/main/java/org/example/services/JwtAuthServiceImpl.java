package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dao.OwnerRepository;
import org.example.dao.UserRepository;
import org.example.entities.owner.Owner;
import org.example.entities.user.User;
import org.example.model.Roles;
import org.example.model.UserDto;
import org.example.requests.JwtCreateCatOwnerRequest;
import org.example.requests.JwtRequest;
import org.example.responses.JwtResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class JwtAuthServiceImpl implements JwtAuthService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final OwnerRepository catOwnerRepository;
    @Override
    public JwtResponse authenticate(JwtRequest jwtRequest) {
        User user = userRepository.findByUsername(jwtRequest.getLogin())
                .orElseThrow(RuntimeException::new);
        var userDto = getUserDtoFromUser(user);
        if (!passwordEncoder.matches(jwtRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException();
        }
        return JwtResponse.builder()
                .token(jwtService.generateToken(userDto))
                .build();
    }
    @Override
    public JwtResponse register(JwtCreateCatOwnerRequest jwtCreateCatOwnerRequest) {
        var catOwner = new Owner(jwtCreateCatOwnerRequest.getLogin(), jwtCreateCatOwnerRequest.getBirthDate());
        catOwnerRepository.save(catOwner);
        var user = User.builder()
                .username(jwtCreateCatOwnerRequest.getLogin())
                .roles(Set.of(Roles.USER))
                .password(passwordEncoder.encode(jwtCreateCatOwnerRequest.getPassword()))
                .catOwner(catOwner)
                .build();
        userRepository.save(user);
        return JwtResponse.builder()
                .token(jwtService.generateToken(getUserDtoFromUser(user)))
                .build();
    }
    private UserDto getUserDtoFromUser(User user) {
        return UserDto.builder()
                .uuid(user.getUuid())
                .username(user.getUsername())
                .catOwnerUuid(user.getCatOwner().getId())
                .roles(user.getRoles())
                .build();
    }
}
