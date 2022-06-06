package com.viktor.task.shoes.manager.web.service;

import java.util.List;
import java.util.Optional;
import com.viktor.task.shoes.manager.web.model.User;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserDetailService extends UserDetailsService {

   

    public void saveUser(User user);

    public void deleteUser(Long id);

    public List<User> getAllUsers();

    public User findByUsername(String username);

    public Optional<User> findById(Long id);

    @Override
    public UserDetails loadUserByUsername(String username);

    Page<User>listPaginated(int pageNo,int pageSize,String sortField,String sortDirection);
}
