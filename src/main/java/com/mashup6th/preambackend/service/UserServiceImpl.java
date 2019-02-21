package com.mashup6th.preambackend.service;


import com.mashup6th.preambackend.dto.user.SignUpJson;
import com.mashup6th.preambackend.dto.user.UserLoginInfo;
import com.mashup6th.preambackend.entity.User;
import com.mashup6th.preambackend.entity.constant.AuthNumber;
import com.mashup6th.preambackend.exception.NotFoundException;
import com.mashup6th.preambackend.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService{
  private UserRepository userRepository;
  private MailSender sender;

  public UserServiceImpl(UserRepository userRepository, MailSender sender) {
    this.userRepository = userRepository;
    this.sender = sender;
  }

  @Override
  public String emailCheck(String email) {
    //중복되면 중복된 email을 보내줌
    log.info("입력한 email은" +  email);
    User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Not found by userId"));;

    log.info("user는" + user);
    if ( user == null){
      return email;
    } else return null;

  }

  @Override
  public boolean nicknameCheck(String nickname) {
    //중복되면 return true
    return userRepository.findByNickname(nickname) != null;
  }

  @Override
  public void save(SignUpJson signUpJson) {
    User user = new User();
    user.setEmail(signUpJson.getEmail());
    user.setNickname(signUpJson.getNickname());
    user.setPassword(signUpJson.getPassword());

    userRepository.save(user);
  }

  @Override
  public User login(UserLoginInfo userLoginInfo) {
    User user = null;

    //이메일이 존재하지 않을 때
    if (userRepository.findByEmail(userLoginInfo.getEmail()) ==  null){
      throw new NotFoundException("해당 이메일에 해당하는 유저가 없습니다.");
    }

    //이메일이 존재하나 비밀번호가 맞지 않을 때
    user = userRepository.findByEmail(userLoginInfo.getEmail()).orElseThrow(() -> new NotFoundException("Not found by userId"));;

    if (! user.getPassword().equals(userLoginInfo.getPassword())) {
      log.info("비밀번호가 틀리네요 숙덕숙덕");
      return null;
    }

    return user;
  }

  @Override
  public String sendEmail(String email) {

    String authNumber = new AuthNumber().makeAuthNumber();

    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setFrom("Pream");
    msg.setTo(email);
    msg.setSubject("[Pream]이메일 인증 번호 확인");
    msg.setText("Pream 어플리케이션에서 인증번호를 입력해주세요. \n 인증번호 : " + authNumber);
    this.sender.send(msg);

    return authNumber;
  }

  // 로그인 상태인지 확인(이메일이 DB에 있는지만)
  @Override
  public boolean checkLogin(String email) {
    return userRepository.findByEmail(email) != null;
  }

  // 사용자의 정보(이메일)를 받아, 닉네임을 구해줌
  @Override
  public String getUserNickname(String email) {
    User user = userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("Not found by userId"));;
    return user.getNickname();
  }
}
