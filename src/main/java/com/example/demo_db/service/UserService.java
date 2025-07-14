package com.example.demo_db.service;

import com.example.demo_db.data.dao.AuthenDAO;
import com.example.demo_db.data.dao.UserDAO;
import com.example.demo_db.data.dto.UserDTO;
import com.example.demo_db.data.entity.AuthenEntity;
import com.example.demo_db.data.entity.UserEntity;
import com.example.demo_db.exception.DuplicateIdException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.DuplicateFormatFlagsException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDAO userDAO;
    private final AuthenDAO authenDAO;


    public List<UserDTO> searchUserInfo(String addr){
        List<UserEntity> userEntityList=this.userDAO.searchUserInfo(addr);
        List<UserDTO> userDTOList=new ArrayList<UserDTO>();
        for(UserEntity userEntity:userEntityList){
            UserDTO userDTO=UserDTO.builder()
                    .userId(userEntity.getUserId())
                    .userName(userEntity.getUserName())
                    .birthYear(userEntity.getBirthYear())
                    .mobile(userEntity.getMobile1()+userEntity.getMobile2())
                    .height(userEntity.getHeight())
                    .joinDate(userEntity.getJoinDate())
                    .addr(userEntity.getAddr())
                    .build();
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }
    public List<UserDTO> searchUserInfo(Integer birthYear){
        List<UserEntity> userEntityList=this.userDAO.searchUserInfo(birthYear);
        List<UserDTO> userDTOList=new ArrayList<UserDTO>();
        for(UserEntity userEntity:userEntityList){
            UserDTO userDTO=UserDTO.builder()
                    .userId(userEntity.getUserId())
                    .userName(userEntity.getUserName())
                    .birthYear(userEntity.getBirthYear())
                    .mobile(userEntity.getMobile1()+userEntity.getMobile2())
                    .height(userEntity.getHeight())
                    .addr(userEntity.getAddr())
                    .joinDate(userEntity.getJoinDate())
                    .build();
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }
    public List<UserDTO> searchUserInfo(String addr, Integer birthYear){
        List<UserEntity> userEntityList=this.userDAO.searchUserInfo(addr, birthYear);
        List<UserDTO> userDTOList=new ArrayList<UserDTO>();
        for(UserEntity userEntity:userEntityList){
            UserDTO userDTO=UserDTO.builder()
                    .userId(userEntity.getUserId())
                    .userName(userEntity.getUserName())
                    .birthYear(userEntity.getBirthYear())
                    .addr(userEntity.getAddr())
                    .mobile(userEntity.getMobile1()+userEntity.getMobile2())
                    .height(userEntity.getHeight())
                    .joinDate(userEntity.getJoinDate())
                    .build();
            userDTOList.add(userDTO);
        }
        return userDTOList;
    }

    public UserDTO joinUserInfo(UserDTO userDTO){//

        UserEntity saveUserEntity=this.userDAO.saveUserEntity(userDTO.getUserId(), userDTO.getUserName(),
                userDTO.getMobile(), userDTO.getBirthYear(), userDTO.getAddr(), userDTO.getHeight(), userDTO.getJoinDate());
        if(saveUserEntity!=null){
            UserDTO saveUserDTO=UserDTO.builder()
                    .userId(saveUserEntity.getUserId())
                    .userName(saveUserEntity.getUserName())
                    .mobile(saveUserEntity.getMobile1()!=null?saveUserEntity.getMobile1()+saveUserEntity.getMobile2():null)
                    .birthYear(saveUserEntity.getBirthYear())
                    .addr(saveUserEntity.getAddr())
                    .height(saveUserEntity.getHeight())
                    .joinDate(saveUserEntity.getJoinDate())
                    .build();

            AuthenEntity authenEntity=this.authenDAO.saveAuthen(userDTO.getUserId(), userDTO.getPassword(), "ROLE_USER");

            return saveUserDTO;
        }
         throw new DuplicateIdException("아이디가 중복되었습니다.");
    }

    public boolean existUserId(String userId) {
        return this.userDAO.existUserId(userId);
    }

    public UserDTO getUserInfo(String userId){
        UserEntity userEntity = this.userDAO.getUserEntityById(userId);
        if(userEntity!=null){
            UserDTO userDTO=UserDTO.builder()
                    .userId(userEntity.getUserId())
                    .userName(userEntity.getUserName())
                    .mobile(userEntity.getMobile1()!=null?userEntity.getMobile1()+userEntity.getMobile2():null)
                    .birthYear(userEntity.getBirthYear())
                    .addr(userEntity.getAddr())
                    .height(userEntity.getHeight())
                    .joinDate(userEntity.getJoinDate())
                    .build();
            return userDTO;
        }
        throw new EntityNotFoundException("없는 아이디입니다.");
    }
}
