package com.avyudaya.blogservice;

import com.avyudaya.blogservice.domain.dto.CreateUserReq;
import com.avyudaya.blogservice.domain.model.Role;
import com.avyudaya.blogservice.service.UserService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DatabaseInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final List<String> usernames =
            List.of("admin@admin.com", "user@user.com");
    private final List<String> fullNames = List.of("Admin Admin", "User User");
    private final List<String> roles = List.of(Role.ADMIN, Role.USER);
    private final String password = "test123";

    private final UserService userService;

    public DatabaseInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        for (var i = 0; i < usernames.size(); ++i) {
            var request =
                    new CreateUserReq(
                            usernames.get(i), fullNames.get(i), password, password, Set.of(roles.get(i)));
            userService.upsert(request);
        }
    }
}
