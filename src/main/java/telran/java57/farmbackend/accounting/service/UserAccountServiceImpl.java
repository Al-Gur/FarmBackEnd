package telran.java57.farmbackend.accounting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import telran.java57.farmbackend.accounting.dao.UserAccountRepository;
import telran.java57.farmbackend.accounting.dto.RolesDto;
import telran.java57.farmbackend.accounting.dto.UserDto;
import telran.java57.farmbackend.accounting.dto.UserRegisterDto;
import telran.java57.farmbackend.accounting.dto.exceptions.UserExistsException;
import telran.java57.farmbackend.accounting.dto.exceptions.UserNotFoundException;
import telran.java57.farmbackend.accounting.model.UserAccount;
import telran.java57.farmbackend.products.dao.ProductsRepository;
import telran.java57.farmbackend.products.dto.OrderDto;
import telran.java57.farmbackend.products.dto.ProductDto;
import telran.java57.farmbackend.products.model.Product;


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
        String password = passwordEncoder.encode(userRegisterDto.getPassword());
        UserAccount user = new UserAccount(userRegisterDto.getLogin(), password, userRegisterDto.getFullName());
//        if (userAccountRepository.count() == 0) {
//           user.addRole("ADMINISTRATOR");
//        }
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
    public UserDto updateUser(String login, String newFullName) {
        UserAccount user = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        if (newFullName != null) {
            user.setFullName(newFullName);
            userAccountRepository.save(user);
        }
        return new UserDto(user);
    }

    @Override
    public RolesDto changeRolesList(String login, String role, boolean isAddRole) {
        UserAccount user = userAccountRepository.findById(login).orElseThrow(UserNotFoundException::new);
        boolean res = isAddRole ? user.addRole(role) : user.removeRole(role);
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
    public Iterable<UserDto> getAllUsers() {
        return userAccountRepository.findAll().stream().map(UserDto::new).toList();
    }

    @Override
    public void run(String... args) throws Exception {
//        if (userAccountRepository.count() == 0) {
//            ;
//        }
        if (!userAccountRepository.existsById("admin")) {
            String password = passwordEncoder.encode("adm11");
            UserAccount user = new UserAccount("admin", password, "admin");//TODO
            user.addRole("ADMINISTRATOR");
            userAccountRepository.save(user);
        }
    }
}
