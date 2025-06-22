package telran.java57.farmbackend.accounting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import telran.java57.farmbackend.accounting.dao.UserAccountRepository;
import telran.java57.farmbackend.accounting.dto.RolesDto;
import telran.java57.farmbackend.accounting.dto.UpdateUserDto;
import telran.java57.farmbackend.accounting.dto.UserDto;
import telran.java57.farmbackend.accounting.dto.UserRegisterDto;
import telran.java57.farmbackend.accounting.dto.exceptions.UserExistsException;
import telran.java57.farmbackend.accounting.dto.exceptions.UserNotFoundException;
import telran.java57.farmbackend.accounting.model.UserAccount;


@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService, CommandLineRunner {
    final UserAccountRepository userAccountRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public UserDto register(UserRegisterDto userRegisterDto) {
        if (userAccountRepository.existsById(userRegisterDto.getLogin())) {
            throw new UserExistsException();
        }
        UserAccount user = new UserAccount(userRegisterDto.getLogin(), userRegisterDto.getPassword(),
                userRegisterDto.getFirstName(), userRegisterDto.getLastName());
        String password = passwordEncoder.encode(userRegisterDto.getPassword());
        user.setPassword(password);
        userAccountRepository.save(user);
        return new UserDto(user);
    }

    @Override
    public UserDto getUser(String login) {
        UserAccount user = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        return new UserDto(user);
    }

    @Override
    public UserDto removeUser(String login) {
        UserAccount user = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        userAccountRepository.delete(user);
        return new UserDto(user);
    }

    @Override
    public UserDto updateUser(String login, UpdateUserDto updateUserDto) {
        UserAccount user = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        if (updateUserDto.getFirstName() != null) {
            user.setFirstName(updateUserDto.getFirstName());
        }
        if (updateUserDto.getLastName() != null) {
            user.setLastName(updateUserDto.getLastName());
        }
        userAccountRepository.save(user);
        return new UserDto(user);
    }

    @Override
    public RolesDto changeRolesList(String login, String role, boolean isAddRole) {
        UserAccount user = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        boolean res;
        if (isAddRole) {
            res = user.addRole(role);
        } else {
            res = user.removeRole(role);
        }
        if (res) {
            userAccountRepository.save(user);
        }
        return new RolesDto(user.getLogin(), user.getRoles());
    }

    @Override
    public void changePassword(String login, String newPassword) {
        UserAccount user = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        String password = passwordEncoder.encode(newPassword);
        user.setPassword(password);
        userAccountRepository.save(user);
    }

    @Override
    public void run(String... args) throws Exception {
        if(!userAccountRepository.existsById("admin")) { //TODO
            String password = passwordEncoder.encode("admin");
            UserAccount user = new UserAccount("admin", password, "admin", "admin");
            user.addRole("ADMINISTRATOR");
            userAccountRepository.save(user);
        }
    }
}
