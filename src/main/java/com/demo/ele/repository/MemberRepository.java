package com.demo.ele.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.demo.ele.model.Member;

/**
 * @author 曾鹏
 * @mail zp@zving.com
 * @date 2018年6月5日
 */
public interface MemberRepository extends CrudRepository<Member, Long> {
	Page<Member> findAll(Pageable pageable);
}
