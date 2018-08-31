package com.demo.ele.ui;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.ele.model.Member;
import com.demo.ele.model.ResponseResult;
import com.demo.ele.repository.MemberRepository;

/**
 * @author 曾鹏
 * @mail zp@zving.com
 * @date 2018年6月5日
 */
@RestController
@RequestMapping("/member")
@CrossOrigin(maxAge = 3600)
public class MemberController {
	public static Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberRepository repository;

	/**
	 * @return 会员列表
	 */
	@GetMapping
	@ResponseBody
	public ResponseResult list(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
		if (page <= 0) {
			page = 1;
		}
		Page<Member> members = repository.findAll(PageRequest.of(--page, size));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", repository.count());
		map.put("list", members.getContent());
		return new ResponseResult(map);
	}

	/**
	 * @param id 会员id
	 * @return 会员详细信息
	 */
	@GetMapping("/{id}")
	@ResponseBody
	public ResponseResult queryById(@PathVariable long id) {
		Optional<Member> optional = repository.findById(id);
		if (optional.isPresent()) {
			return new ResponseResult(optional.get());
		} else {
			return new ResponseResult(ResponseResult.FAIL, "用户不存在!");
		}
	}

	/**
	 * @param member
	 * @return 添加会员
	 */
	@PostMapping(consumes = "application/json")
	@ResponseBody
	public ResponseResult add(@RequestBody Member member) {
		member.setStatus("Y");
		member.setAddTime(new Date());
		member.setAddUser(member.getUserName());
		Member savedMember = repository.save(member);
		if (savedMember.getId() > 0) {
			logger.info(String.format("%s 新建成功.", member));
			return new ResponseResult(ResponseResult.SUCCESS, "操作成功!");
		} else {
			logger.error("%s 新建失败.", member);
			return new ResponseResult(ResponseResult.FAIL, "操作失败!");
		}
	}

	@PutMapping(consumes = "application/json")
	@ResponseBody
	public ResponseResult update(@RequestBody Member member) {
		Optional<Member> optional = repository.findById(member.getId());
		if (optional.isPresent()) {
			Member m = optional.get();
			m.setEmail(member.getEmail());
			m.setMobile(member.getMobile());
			m.setRealName(member.getRealName());
			m.setGender(member.getGender());
			m.setStatus(member.getStatus());
			try {
				repository.save(m);
				return new ResponseResult(ResponseResult.SUCCESS, "操作成功!");
			} catch (Exception e) {
				return new ResponseResult(ResponseResult.FAIL, e.getMessage());
			}
		} else {
			return new ResponseResult(ResponseResult.FAIL, "会员不存在!");
		}
	}

	/**
	 * @param id
	 * @return 删除会员
	 */
	@DeleteMapping("/{id}")
	@ResponseBody
	public ResponseResult delete(@PathVariable long id) {
		try {
			repository.deleteById(id);
			logger.info(String.format("Member[%d] 删除成功.", id));
			return new ResponseResult(ResponseResult.SUCCESS, "操作成功!");
		} catch (Exception e) {
			logger.error("Member[%d] 删除失败.", id);
			return new ResponseResult(ResponseResult.FAIL, e.getMessage());
		}
	}
}
