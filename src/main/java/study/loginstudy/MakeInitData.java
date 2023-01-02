package study.loginstudy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import study.loginstudy.domain.UserRole;
import study.loginstudy.domain.entity.User;
import study.loginstudy.repository.UserRepository;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class MakeInitData {

    private final UserRepository userRepository;

    @PostConstruct
    public void makeAdminAndUser() {
        User admin = User.builder()
                .loginId("admin")
                .password("1234")
                .nickname("관리자")
                .role(UserRole.ADMIN)
                .build();
        userRepository.save(admin);

        User user = User.builder()
                .loginId("user")
                .password("1234")
                .nickname("User1")
                .role(UserRole.USER)
                .build();
        userRepository.save(user);
    }
}
