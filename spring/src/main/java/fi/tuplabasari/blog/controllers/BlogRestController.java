package fi.tuplabasari.blog.controllers;

import fi.tuplabasari.blog.creation.BlogPost;
import fi.tuplabasari.blog.creation.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin
@RestController
public class BlogRestController {

    @Autowired
    BlogRepository blogRepository;

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public void addPost(@RequestBody BlogPost post, UriComponentsBuilder b) {
        blogRepository.saveEntry(post);
    }

    @RequestMapping(value = "/posts/", method = RequestMethod.GET)
    public Iterable<BlogPost> getPosts() {
        return blogRepository.getAll();
    }

    @RequestMapping(value = "/posts/{postId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deletePost(@PathVariable int postId) {
        blogRepository.deleteEntry(postId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/posts/{postId}", method = RequestMethod.GET)
    public BlogPost getPost(@PathVariable int postId) {
        return blogRepository.getOne(postId);
    }

}
