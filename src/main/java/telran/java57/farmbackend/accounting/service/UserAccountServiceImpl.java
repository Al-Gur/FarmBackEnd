package telran.java57.farmbackend.accounting.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.java57.farmbackend.accounting.dto.UserDto;
import telran.java57.farmbackend.accounting.dto.UserRegisterDto;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {
    @Override
    public UserDto register(UserRegisterDto userRegisterDto) {
        return null;
    }
}
