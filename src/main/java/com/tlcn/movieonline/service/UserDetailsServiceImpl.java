//package com.tlcn.movieonline.service;
//
//import com.tlcn.movieonline.model.Role;
//import com.tlcn.movieonline.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.tlcn.movieonline.model.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;
//        User user= userRepository.findByEmail(email);
//        // check username has in database
//        if (user==null)
//            throw new UsernameNotFoundException("User not found with "+email);
//        //get role of user, put to grandAuthoritySet
//        Set<GrantedAuthority> grantedAuthoritySet= new HashSet<>();
//        for (Role role: user.getRoles()){
//            grantedAuthoritySet.add(new SimpleGrantedAuthority(role.getType()));
//        }
//
//        // return userdetails to spring security manager
//        return new org.springframework.security.core.userdetails.User(
//                user.getEmail(),
//                user.getPassword(),
//                user.isEnable(),
//                accountNonExpired,
//                credentialsNonExpired,
//                accountNonLocked,
//                grantedAuthoritySet);
//    }
//}
