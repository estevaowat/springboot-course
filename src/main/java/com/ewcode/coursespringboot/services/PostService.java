package com.ewcode.coursespringboot.services;

import com.ewcode.coursespringboot.domain.Post;
import com.ewcode.coursespringboot.repository.PostRepository;
import com.ewcode.coursespringboot.services.expection.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;


    public Post findById (String id) {
        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            throw new ObjectNotFoundException("Objeto não encontrado");
        }
        return post;
    }

    public List<Post> findByTitle (String text) {
        return postRepository.searchTitle(text);
    }

    public List<Post> fullSearch (String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);

        return postRepository.fullSearch(text, minDate, maxDate);
    }
}
