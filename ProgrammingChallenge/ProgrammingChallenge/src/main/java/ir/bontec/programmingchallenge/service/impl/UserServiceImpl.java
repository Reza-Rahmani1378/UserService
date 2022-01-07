package ir.bontec.programmingchallenge.service.impl;

import ir.bontec.programmingchallenge.base.service.impl.BaseServiceImpl;
import ir.bontec.programmingchallenge.controller.RequestOperationName;
import ir.bontec.programmingchallenge.entities.User;
import ir.bontec.programmingchallenge.repository.UserRepository;
import ir.bontec.programmingchallenge.resources.UserMapper;
import ir.bontec.programmingchallenge.service.UserService;
import ir.bontec.programmingchallenge.service.dto.UserDTO;
import ir.bontec.programmingchallenge.ui.model.request.UserDetailsRequestModel;
import ir.bontec.programmingchallenge.ui.model.response.OperationStatusModel;
import ir.bontec.programmingchallenge.ui.model.response.RequestOperationStatus;
import ir.bontec.programmingchallenge.ui.model.response.UserRest;
import ir.bontec.programmingchallenge.ui.model.response.errormessages.ErrorMessages;
import ir.bontec.programmingchallenge.util.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository>
        implements UserService {

    private final UserMapper userMapper;
    private final Utils utils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository repository, UserMapper userMapper, Utils utils, BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(repository);
        this.userMapper = userMapper;
        this.utils = utils;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User saveNotSecure(User user) {
        return super.saveNotSecure(user);
    }

    @Override
    public List<User> saveAllNotSecure(Collection<User> collection) {
        return super.saveAllNotSecure(collection);
    }

    @Override
    public Optional<User> findByIdNotSecure(Long id) {
        return super.findByIdNotSecure(id);
    }

    @Override
    public List<User> findAllNotSecure() {
        return super.findAllNotSecure();
    }

    @Override
    public void deleteByIdNotSecure(Long id) {
        super.deleteByIdNotSecure(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getEncryptedPassword(), new ArrayList<>()
        );
    }

    @Override
    public UserDTO getUser(String username) {
        User userEntity = repository.getUserByUsername(username);

        if (userEntity == null) throw new UsernameNotFoundException(username);

        return userMapper.convertEntityToDTO(userEntity);
    }

    @Override
    public UserRest getUserByUserId(String id) {
        User entity = repository.getUserByUserId(id);

        if (entity == null) throw new UsernameNotFoundException("User With id " + id + "not found");

        UserRest userRest = new UserRest();
        BeanUtils.copyProperties(userMapper.convertEntityToDTO(entity), userRest);
        return userRest;
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetails) {
        UserRest returnUserRest = new UserRest();
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDetails, userDTO);

        if (repository.getUserByUsername(userDTO.getUsername()) != null) {
            throw new RuntimeException("Record Already Exist");
        }

        User storedUser = userMapper.convertDTOToEntity(userDTO);
        storedUser.setEncryptedPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        storedUser.setUserId(utils.generateUserId(50));
        storedUser = super.saveNotSecure(storedUser);

        BeanUtils.copyProperties(userMapper.convertEntityToDTO(storedUser), returnUserRest);
        return returnUserRest;
    }

    @Override
    public UserRest updateUser(String id, UserDetailsRequestModel userDetails) {
        UserRest updateUserRest = new UserRest();
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDetails, userDTO);
        User userEntity = repository.getUserByUserId(id);

        if (userEntity == null)
            throw new UsernameNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        userEntity = super.saveNotSecure(userMapper.convertDTOToEntity(userDTO));

        BeanUtils.copyProperties(userMapper.convertEntityToDTO(userEntity), updateUserRest);

        return updateUserRest;

    }

    @Override
    public OperationStatusModel deleteUserByUserId(String id) {
        User userEntity = repository.getUserByUserId(id);

        if (userEntity == null)
            throw new UsernameNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.DELETE.name());
        repository.delete(userEntity);
        returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnValue;
    }

    @Override
    public List<UserRest> getUsers(int page, int limit) {
        List<UserRest> userRests = new ArrayList<>();
        if (page > 0)
            page--;
        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<User> usersPage = repository.findAll(pageableRequest);

        List<UserDTO> userDTOS = userMapper.convertListEntityToDTO(usersPage.getContent());

        for (UserDTO userDTO : userDTOS) {
            UserRest userModel = new UserRest();
            BeanUtils.copyProperties(userDTO, userModel);
            userRests.add(userModel);
        }
        return userRests;

    }

    @Override
    public OperationStatusModel requestPasswordReset(String username) {

        OperationStatusModel returnedValue = new OperationStatusModel();
        returnedValue.setOperationName(RequestOperationName.REQUEST_PASSWORD_RESET.name());
        returnedValue.setOperationResult(RequestOperationStatus.ERROR.name());
        User userEntity = repository.getUserByUsername(username);
        if (userEntity == null)
            return returnedValue;
        boolean check = false;


        // Generate Password Token
        String token = utils.generatePasswordToken(userEntity.getUserId());
        PasswordResetTokenEntity passwordResetTokenEntity = new PasswordResetTokenEntity();
        passwordResetTokenEntity.setToken(token);
        passwordResetTokenEntity.setUserDetails(userEntity);
        passwordResetTokenRepository.save(passwordResetTokenEntity);

        check = new AmazonSES().sendPasswordResetRequest(
                userEntity.getFirstName(),
                userEntity.getEmail(),
                token
        );
        if (check)
            returnedValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        return returnedValue;
    }
}
