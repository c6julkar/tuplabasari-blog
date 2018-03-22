package fi.tuplabasari.blog.creation;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogRepositoryImpl implements BlogRepository {

    private List<BlogPost> blogPosts;

    @PostConstruct
    public synchronized void init() {
        blogPosts = new ArrayList<>();
        blogPosts.add(new BlogPost("fdsafd", "fdssda"));
        blogPosts.add(new BlogPost("fddfsgsdgfdsgsdg", "fdgsdfgdsgsdfgdssda"));
    }

    @Override
    public synchronized BlogPost saveEntry(BlogPost entry) {
        blogPosts.add(entry);
        return entry;
    }

    @Override
    public synchronized void deleteEntry(Integer integer) {

        BlogPost removable = null;

        for (BlogPost post : blogPosts) {
            if (post.getId() == integer) {
                removable = post;
                break;
            }
        }

        blogPosts.remove(removable);

    }

    @Override
    public synchronized Iterable<BlogPost> getAll() {
        return blogPosts;
    }

    @Override
    public synchronized BlogPost getOne(Integer integer) {
        BlogPost target = null;

        for (BlogPost post : blogPosts) {
            if (post.getId() == integer) {
                target = post;
                break;
            }
        }

        return new BlogPost(target.getId(), target.getHeader(), target.getContent());
    }
}
