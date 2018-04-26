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
        blogPosts.add(new BlogPost("Eka blogi", " Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus condimentum non est id lobortis. Sed purus justo, commodo volutpat ex ac, convallis sodales mauris. Phasellus congue porta lobortis. Nunc a elementum lorem. Morbi porttitor, dui ac congue semper, justo neque tempor diam, non vulputate velit lorem nec urna. Sed et quam pretium, pulvinar leo ut, eleifend orci. Proin ut mi nisl. Nam sollicitudin euismod augue ac tincidunt.\n" +
                "\n" +
                "Phasellus euismod orci a ipsum tincidunt, ut vehicula neque aliquet. Nulla pellentesque condimentum dignissim. Nullam sodales ante tempor malesuada porttitor. Ut ut massa eu purus pellentesque interdum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Cras efficitur, augue aliquam cursus elementum, diam tortor tempus odio, vitae tempus nisi sem non orci. In sed nulla massa. Praesent pulvinar euismod nisl, vel aliquam ex posuere quis. Maecenas consectetur tincidunt purus quis rhoncus. Quisque malesuada, velit in tempor sodales, mi nisi scelerisque mauris, et dictum massa odio at augue. "));
        blogPosts.add(new BlogPost("Toka blogi", " Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus condimentum non est id lobortis. Sed purus justo, commodo volutpat ex ac, convallis sodales mauris. Phasellus congue porta lobortis. Nunc a elementum lorem. Morbi porttitor, dui ac congue semper, justo neque tempor diam, non vulputate velit lorem nec urna. Sed et quam pretium, pulvinar leo ut, eleifend orci. Proin ut mi nisl. Nam sollicitudin euismod augue ac tincidunt.\n" +
                "\n" +
                "Phasellus euismod orci a ipsum tincidunt, ut vehicula neque aliquet. Nulla pellentesque condimentum dignissim. Nullam sodales ante tempor malesuada porttitor. Ut ut massa eu purus pellentesque interdum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Cras efficitur, augue aliquam cursus elementum, diam tortor tempus odio, vitae tempus nisi sem non orci. In sed nulla massa. Praesent pulvinar euismod nisl, vel aliquam ex posuere quis. Maecenas consectetur tincidunt purus quis rhoncus. Quisque malesuada, velit in tempor sodales, mi nisi scelerisque mauris, et dictum massa odio at augue. "));
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
