package com.single.commerce_project.member.repository;

import com.single.commerce_project.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByEmailAuthKey(String uuid);

    Optional<Member> findByUserNameAndEmail(String userName, String email);

}
