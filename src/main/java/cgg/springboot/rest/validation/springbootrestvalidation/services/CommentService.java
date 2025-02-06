package cgg.springboot.rest.validation.springbootrestvalidation.services;

import cgg.springboot.rest.validation.springbootrestvalidation.dto.CommentDTO;

public interface CommentService {
    CommentDTO createComment(CommentDTO commentDTO, Integer postId);

    void deleteComment(Integer commentId);
}
