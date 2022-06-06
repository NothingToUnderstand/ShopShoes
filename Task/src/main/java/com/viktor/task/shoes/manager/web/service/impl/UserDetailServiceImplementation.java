package com.viktor.task.shoes.manager.web.service.impl;

import com.viktor.task.shoes.manager.web.dao.UserDao;
import com.viktor.task.shoes.manager.web.model.Role;
import com.viktor.task.shoes.manager.web.model.User;
import com.viktor.task.shoes.manager.web.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
public class UserDetailServiceImplementation implements UserDetailService {
    @Autowired
    private final UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    

    @Autowired
    public UserDetailServiceImplementation(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setRoles(Collections.singleton(new Role(2L, "ROLE_USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        this.userDao.deleteById(id);

    }

    @Override
    @Transactional
    public Optional<User> findById(Long id) {
        return this.userDao.findById(id);

    }

    @Override
    @Transactional
    public User findByUsername(String username) {
        return this.userDao.findByUsername(username);

    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return this.userDao.findAll();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not Found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.getRoles());
        // mapRolesToAuthorities(user.getRoles()));
    }

    @Override
    public Page<User> listPaginated(int pageNo, int pageSize,String sortField,String sortDirection) {
       Sort sort=sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
       Sort.by(sortField).descending();
        Pageable pageable=PageRequest.of(pageNo-1, pageSize,sort);
        return this.userDao.findAll(pageable);
    }

    // private Collection<? extends GrantedAuthority>
    // mapRolesToAuthorities(Collection<Role> roles) {
    // return roles.stream().map(r -> new
    // SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    // } {

}
