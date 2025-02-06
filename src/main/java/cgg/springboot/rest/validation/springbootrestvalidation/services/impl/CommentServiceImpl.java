package cgg.springboot.rest.validation.springbootrestvalidation.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cgg.springboot.rest.validation.springbootrestvalidation.dao.CommentRepo;
import cgg.springboot.rest.validation.springbootrestvalidation.dao.PostRepo;
import cgg.springboot.rest.validation.springbootrestvalidation.dto.CommentDTO;
import cgg.springboot.rest.validation.springbootrestvalidation.entities.Comment;
import cgg.springboot.rest.validation.springbootrestvalidation.entities.Post;
import cgg.springboot.rest.validation.springbootrestvalidation.exceptions.ResourceNotFoundException;
import cgg.springboot.rest.validation.springbootrestvalidation.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
        Post post = this.postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        Comment comment = this.modelMapper.map(commentDTO, Comment.class);
        comment.setPost(post);
        Comment savedComment = commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepo.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", commentId));
        this.commentRepo.delete(comment);
    }

}
