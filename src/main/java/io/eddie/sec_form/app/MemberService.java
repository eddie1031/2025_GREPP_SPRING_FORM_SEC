package io.eddie.sec_form.app;

import io.eddie.sec_form.dao.MemberRepository;
import io.eddie.sec_form.domain.Member;
import io.eddie.sec_form.dto.MemberDetails;
import io.eddie.sec_form.dto.SignUpForm;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    public void save(SignUpForm signUpForm) {

        Member member = Member.builder()
                .username(signUpForm.getUsername())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .email(signUpForm.getEmail())
                .build();

        memberRepository.save(member);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        memberRepository.findByUs
        Optional<Member> memberOptional = memberRepository.findByUsername(username);
        Member findMember = memberOptional.orElseThrow(
                () -> new UsernameNotFoundException("Username " + username + " not found")
        );

//        return new User(findMember.getUsername(), findMember.getPassword(), List.of(new SimpleGrantedAuthority(findMember.getRole())));
        return new MemberDetails(findMember);

    }

}
