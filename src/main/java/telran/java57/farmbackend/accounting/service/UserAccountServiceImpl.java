package telran.java57.farmbackend.accounting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import telran.java57.farmbackend.accounting.dao.UserAccountRepository;
import telran.java57.farmbackend.accounting.dto.RolesDto;
import telran.java57.farmbackend.accounting.dto.UpdateUserDto;
import telran.java57.farmbackend.accounting.dto.UserDto;
import telran.java57.farmbackend.accounting.dto.UserRegisterDto;
import telran.java57.farmbackend.accounting.dto.exceptions.UserExistsException;
import telran.java57.farmbackend.accounting.model.User;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    final UserAccountRepository userAccountRepository;
    final PasswordEncoder passwordEncoder;

    @Override
    public UserDto register(UserRegisterDto userRegisterDto) {
        if (userAccountRepository.existsById(userRegisterDto.getLogin())) {
            throw new UserExistsException("User already exists");
        }
        User user = new User(userRegisterDto.getLogin(), userRegisterDto.getPassword(),
                userRegisterDto.getFirstName(), userRegisterDto.getLastName());
        String password = passwordEncoder.encode(userRegisterDto.getPassword());
        user.setPassword(password);
        userAccountRepository.save(user);
        return new UserDto(user);
    }

    @Override
    public UserDto getUser(String login) {
        UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public UserDto removeUser(String login) {
        UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        userAccountRepository.delete(userAccount);
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public UserDto updateUser(String login, UpdateUserDto updateUserDto) {
        UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        if (updateUserDto.getFirstName() != null) {
            userAccount.setFirstName(updateUserDto.getFirstName());
        }
        if (updateUserDto.getLastName() != null) {
            userAccount.setLastName(updateUserDto.getLastName());
        }
        userAccountRepository.save(userAccount);
        return modelMapper.map(userAccount, UserDto.class);
    }

    @Override
    public RolesDto changeRolesList(String login, String role, boolean isAddRole) {
        UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        boolean res;
        if (isAddRole) {
            res = userAccount.addRole(role);
        } else {
            res = userAccount.removeRole(role);
        }
        if (res) {
            userAccountRepository.save(userAccount);
        }
        return modelMapper.map(userAccount, RolesDto.class);
    }

    @Override
    public void changePassword(String login, String newPassword) {
        UserAccount userAccount = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        String password = passwordEncoder.encode(newPassword);
        userAccount.setPassword(password);
        userAccountRepository.save(userAccount);
    }

    @Override
    public void run(String... args) throws Exception {
        if(!userAccountRepository.existsById("admin")) {
            String password = passwordEncoder.encode("admin");
            UserAccount userAccount = new UserAccount("admin", password, "admin", "admin");
            userAccount.addRole(Role.MODERATOR.name());
            userAccount.addRole(Role.ADMINISTRATOR.name());
            userAccountRepository.save(userAccount);
        }
    }




    @Override
    public UserDto getUser(String name) {
        return null;
    }

    @Override
    public void changePassword(String name, String newPassword) {

    }

    @Override
    public UserDto removeUser(String login) {
        return null;
    }

    @Override
    public UserDto updateUser(String login, UpdateUserDto updateUserDto) {
        return null;
    }

    @Override
    public RolesDto changeRolesList(String login, String role, boolean b) {
        return null;
    }
}
