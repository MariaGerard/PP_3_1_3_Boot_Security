package ru.kata.spring.boot_security.demo.init;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UsersService;
import javax.annotation.PostConstruct;

@Component
public class Init {

    private final RoleRepository roleRepository;
    private final UsersService usersService;

    @Autowired
    public Init(RoleRepository roleRepository, UsersService usersService) {
        this.roleRepository = roleRepository;
        this.usersService = usersService;
    }

    @PostConstruct
    public void initTestUsers() {
        Role userTest = new Role("ROLE_USER");
        Role adminTest = new Role("ROLE_ADMIN");
        roleRepository.save(userTest);
        roleRepository.save(adminTest);
        Set<Role> userTestSet = Stream.of(userTest).collect(Collectors.toSet());
        Set<Role> adminTestSet = Stream.of(adminTest).collect(Collectors.toSet());

        User user = new User("TestUser", "TestUser", 21, "test", userTestSet);
        User admin = new User("TestAdmin", "TestAdmin", 21, "test", adminTestSet);
        usersService.save(user);
        usersService.save(admin);

    }
}
