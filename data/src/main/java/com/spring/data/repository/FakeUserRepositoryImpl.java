//package com.spring.data.repository;
//
//import com.spring.data.config.UserRole;
//import com.spring.data.entity.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.repository.query.FluentQuery;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Function;
//
//@Service("fakeUserRepo")
//public class FakeUserRepositoryImpl implements UserRepository {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public Optional<User> selectApplicationUserByUsername(String username) {
//        return getUsers().stream().filter( user -> user.getUsername().equalsIgnoreCase(username))
//                .findFirst();
//    }
//
//        public List<User> getUsers(){
//                return Arrays.asList(
//                            new User("phuc", passwordEncoder.encode("phuc1"), "phuc@gmail.com", UserRole.STUDENT,
//                                            UserRole.STUDENT.getGrantedAuthorities(), true,
//                                            true, true, true),
//                            new User("ben", passwordEncoder.encode("ben1"), "ben@gmail.com", UserRole.TEACHER,
//                                            UserRole.TEACHER.getGrantedAuthorities(), true,
//                                           true, true, true),
//                            new User("ngoc", passwordEncoder.encode("ngoc1"), "ngoc@gmail.com", UserRole.ADMIN,
//                                            UserRole.ADMIN.getGrantedAuthorities(), true,
//                                            true, true, true)
//                            );
//            }
//
//    @Override
//    public List<User> findAll() {
//        return findAll();
//    }
//
//    @Override
//    public List<User> findAll(Sort sort) {
//        return findAll(sort);
//    }
//
//    @Override
//    public Page<User> findAll(Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public List<User> findAllById(Iterable<Long> longs) {
//        return null;
//    }
//
//    @Override
//    public long count() {
//        return 0;
//    }
//
//    @Override
//    public void deleteById(Long aLong) {
//
//    }
//
//    @Override
//    public void delete(User entity) {
//
//    }
//
//    @Override
//    public void deleteAllById(Iterable<? extends Long> longs) {
//
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends User> entities) {
//
//    }
//
//    @Override
//    public void deleteAll() {
//
//    }
//
//    @Override
//    public <S extends User> S save(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends User> List<S> saveAll(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public Optional<User> findById(Long aLong) {
//        return Optional.empty();
//    }
//
//    @Override
//    public boolean existsById(Long aLong) {
//        return false;
//    }
//
//    @Override
//    public void flush() {
//
//    }
//
//    @Override
//    public <S extends User> S saveAndFlush(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    public void deleteAllInBatch(Iterable<User> entities) {
//
//    }
//
//    @Override
//    public void deleteAllByIdInBatch(Iterable<Long> longs) {
//
//    }
//
//    @Override
//    public void deleteAllInBatch() {
//
//    }
//
//    @Override
//    public User getOne(Long aLong) {
//        return null;
//    }
//
//    @Override
//    public User getById(Long aLong) {
//        return null;
//    }
//
//    @Override
//    public User getReferenceById(Long aLong) {
//        return null;
//    }
//
//    @Override
//    public <S extends User> Optional<S> findOne(Example<S> example) {
//        return Optional.empty();
//    }
//
//    @Override
//    public <S extends User> List<S> findAll(Example<S> example) {
//        return null;
//    }
//
//    @Override
//    public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
//        return null;
//    }
//
//    @Override
//    public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    public <S extends User> long count(Example<S> example) {
//        return 0;
//    }
//
//    @Override
//    public <S extends User> boolean exists(Example<S> example) {
//        return false;
//    }
//
//    @Override
//    public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
//        return null;
//    }
//}
