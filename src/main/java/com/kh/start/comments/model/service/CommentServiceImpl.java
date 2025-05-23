package com.kh.start.comments.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.auth.service.AuthService;
import com.kh.start.board.model.service.BoardService;
import com.kh.start.comments.model.dao.CommentMapper;
import com.kh.start.comments.model.dto.CommentDTO;
import com.kh.start.comments.model.vo.Comment;
import com.kh.start.exception.InvalidUserREquestException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentMapper commentMapper;
    private final BoardService boardService;
    private final AuthService authService;

    @Override
    public void insertComment(CommentDTO comment) {

        // 게시글 번호가 존재하는지 확인
        boardService.findById(comment.getRefBoardNo());

        // 요청한 사용자랑 토큰 소유주가 같은지 확인
        String tokenMemberNo= String.valueOf(((CustomUserDetails)authService.getUserDetails()).getMemberNo());

        if (!tokenMemberNo.equals(comment.getCommentWriter())){
            throw new InvalidUserREquestException("유효하지 않음");
        }
        Comment requestData = Comment.builder()
                                    .commentWriter(Long.parseLong(tokenMemberNo))
                                    .commentContent(comment.getCommentContent())
                                    .refBoardNo(comment.getRefBoardNo())
                                    .build();
        commentMapper.insertComment(requestData);

    }

    @Override
    public List<CommentDTO> selectCommentList(Long boardNo) {
        boardService.findById(boardNo);
        return commentMapper.selectCommentList(boardNo);
    }

}
