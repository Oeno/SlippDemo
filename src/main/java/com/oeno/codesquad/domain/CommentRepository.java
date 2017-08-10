package com.oeno.codesquad.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
	public List<Comment> findByQuestionIndexOrderByIndex(Long questionIndex);
}
