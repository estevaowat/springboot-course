package com.ewcode.coursespringboot.services;

import com.ewcode.coursespringboot.domain.User;
import com.ewcode.coursespringboot.dto.UserDTO;
import com.ewcode.coursespringboot.repository.UserRepository;
import com.ewcode.coursespringboot.services.expection.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll () {
        return userRepository.findAll();
    }

    public User findById (String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        return user;
    }

    public User insert (User user) {
        return userRepository.insert(user);
    }

    public User update (User user) {
        User userFromDB = findById(user.getId());
        updateData(userFromDB, user);
        return userRepository.save(userFromDB);
    }

    private void updateData (User userFromDB, User user) {
        userFromDB.setName(user.getName());
        userFromDB.setEmail(user.getEmail());
    }

    public void delete (String id) {
        findById(id);
        userRepository.deleteById(id);
    }

    public User fromDTO (UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }
}
