//package com.tlcn.movieonline.service;
//
//import com.tlcn.movieonline.dto.RegisterRequest;
//import com.tlcn.movieonline.model.Role;
//import com.tlcn.movieonline.model.User;
//import com.tlcn.movieonline.repository.RoleRepository;
//import com.tlcn.movieonline.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Service
//public class UserServiceImpl implements UserService{
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Override
//    public Boolean emailExist(String email) {
//        User user =userRepository.findByEmail(email);
//        if (user ==null)
//            return false;
//        return true;
//    }
//
//    @Override
//    public User registerAccount(RegisterRequest registerRequest){
//        User user= new User();
//        if (emailExist(registerRequest.getEmail())==false){
//            Set<Role> roles= new HashSet<>();
//            List<Role> lstRole=roleRepository.findAll();
//            roles.add(lstRole.get(1));
//            user.setName(registerRequest.getName());
//            user.setEmail(registerRequest.getEmail());
//            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
//            user.setRoles(roles);
//            userRepository.save(user);
//        }
//        return user;
//    }
//}
