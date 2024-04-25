package fa.training.blog.controller;

import fa.training.blog.payload.request.PostContentReq;
import fa.training.blog.service.HomeService;
import fa.training.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostController {
    @Autowired
    private HomeService homeService;

    @Autowired
    private PostService postService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/managepost")
    public String getManagePost(Model model){
        model.addAttribute("content", homeService.getAllContentOfAuthor());
        return "manage-post";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/createpost")
    public String createPost(Model model){
        model.addAttribute("post", new PostContentReq());
        return "create-post";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/createpost")
    public String createPost(@Valid @ModelAttribute PostContentReq postContentReq, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "redirect:/createpost";
        }
        System.out.println("Saved"+postService.createPost(postContentReq));
        return "redirect:/home";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = {"/deletepost"})
    public String deletePost(@RequestParam(value = "id",required = false)Integer id){
        System.err.println("---------id: "+id);
        if (id != null){
            postService.deletePost(id);
        }
        return "redirect:/managepost";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = {"/editpost"})
    public String edit(Model model, @RequestParam(value ="id", required = false)Integer id, HttpSession session){
        if (id == null){
            return  "redirect:/managepost";
        }
        model.addAttribute("post",postService.getPostContentReqByIdAndAuthorId(id));
        return "create-post";
    }
}
