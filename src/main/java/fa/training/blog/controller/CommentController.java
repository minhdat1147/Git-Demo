package fa.training.blog.controller;

import fa.training.blog.payload.request.CommentContentReq;
import fa.training.blog.payload.response.CommentContentResp;
import fa.training.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/comment")
    public String createComment(Model model, @RequestParam(value = "id", required = false) Integer id){
        if (id == null){
            return "redirect:/home";
        }
        CommentContentResp commentContentResp = commentService.getComment(id);
        if (commentContentResp == null){
            return "redirect:/home";
        }
        model.addAttribute("commentContentResp",commentContentResp);
        model.addAttribute("comment",new CommentContentReq());
        return "create-comment";
    }

    @PostMapping("/comment")
    public String createComment(Model model, @Valid @ModelAttribute CommentContentReq contentReq, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            System.err.println(contentReq);
            CommentContentResp commentContentResp = commentService.getComment(contentReq.getPostId());
            model.addAttribute("commentContentResp", new CommentContentReq());
            return "create-comment";
        }
        commentService.addComment(contentReq);
        return "redirect:/home";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/mcomments")
    public String getAllComment(Model model, @RequestParam(value="id", required = false) Integer id){
        if (id == null){
            return"redirect:/managepost";
        }
        CommentContentResp commentContentResp = commentService.getAllCommentInPost(id);
        if(commentContentResp == null){
            return "redirect:/managepost";
        }
        model.addAttribute("commentContentResp", commentContentResp);
        model.addAttribute("comment", new CommentContentReq());
        return "manage-comment";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/approvecmt")
    public String approveComment(@RequestParam(value="id", required = false)Integer id){
        if (id !=null){
            commentService.approveComment(id);
        }
        return "redirect:/managepost";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/deletecmt")
    public String deleteComment(@RequestParam(value = "id", required = false)Integer id){
        if (id != null){
            commentService.deleteComment(id);
        }
        return "redirect:/managepost";
    }
}
