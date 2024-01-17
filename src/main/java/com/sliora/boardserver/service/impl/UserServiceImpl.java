package com.sliora.boardserver.service.impl;

import com.sliora.boardserver.dto.UserDTO;
import com.sliora.boardserver.exception.DuplicateIdException;
import com.sliora.boardserver.mapper.UserProfileMapper;
import com.sliora.boardserver.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.sliora.boardserver.utils.SHA256Util.encryptSHA256;

@RequiredArgsConstructor
@Service
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserProfileMapper userProfileMapper;

    @Override
    public void register(UserDTO userProfile) {
        boolean IsIdResult = isDuplicatedId(userProfile.getUserId());
        if(IsIdResult) {
            throw new DuplicateIdException("중복된 아이디입니다.");
        }

        userProfile.setCreateTime(new Date());
        userProfile.setPassword(encryptSHA256(userProfile.getPassword()));
        int insertCount = userProfileMapper.register(userProfile);

        if (insertCount != 1) {
            log.error("insertMember ERROR! {}", userProfile);
            throw new RuntimeException(
                    "insertUser ERROR! 회원가입 메서드를 확인해주세요.\n"
                    +"Params: " + userProfile
            );
        }
    }

    @Override
    public UserDTO login(String id, String password) {
        String cryptoPassword = encryptSHA256(password);
        UserDTO userDTO = userProfileMapper.findByIdAndPassword(id, cryptoPassword);

        return userDTO;
    }

    @Override
    public boolean isDuplicatedId(String id) {
        return userProfileMapper.idCheck(id) == 1;
    }

    @Override
    public UserDTO getUserInfo(String userId) {
        return null;
    }

    @Override
    public void updatePassword(String id, String beforePassword, String afterPassword) {
        String cryptoPassword = encryptSHA256(beforePassword);
        UserDTO userDTO = userProfileMapper.findByIdAndPassword(id, cryptoPassword);

        if(userDTO != null) {
            userDTO.setPassword(encryptSHA256(afterPassword));
            userProfileMapper.updatePassword(userDTO);
        } else {
            log.error("updatePassword ERROR! {}", userDTO);
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
    }

    @Override
    public void deleteId(String id, String passWord) {
        String cryptoPassword = encryptSHA256(passWord);
        UserDTO memberInfo = userProfileMapper.findByIdAndPassword(id, cryptoPassword);

        if (memberInfo != null) {
            userProfileMapper.deleteUserProfile(id);
        } else {
            log.error("deleteId ERROR! {}", memberInfo);
            throw new RuntimeException("deleteId ERROR! id 삭제 메서드를 확인해주세요\n" + "Params : " + memberInfo);
        }
    }
}
