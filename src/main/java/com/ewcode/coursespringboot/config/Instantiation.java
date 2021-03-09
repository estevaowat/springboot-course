package com.ewcode.coursespringboot.config;


import com.ewcode.coursespringboot.domain.Post;
import com.ewcode.coursespringboot.domain.User;
import com.ewcode.coursespringboot.dto.AuthorDTO;
import com.ewcode.coursespringboot.dto.CommentDTO;
import com.ewcode.coursespringboot.repository.PostRepository;
import com.ewcode.coursespringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run (String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User naruto = new User(null, "Naruto Uzumaki", "naruto@konoha.jp");
        User sasuke = new User(null, "Sasuke Uchiha", "sasuke@konoha.jp");
        User sakura = new User(null, "Sakura Haruno", "sakura@konoha.jp");
        userRepository.insert(Arrays.asList(naruto, sasuke, sakura));


        Post post1 = new Post(null, sdf.parse("03/03/2021"), "Frase do dia", "Vou me tornar um Hokage, tô certo", new AuthorDTO(naruto));
        Post post2 = new Post(null, sdf.parse("03/03/2021"), "Novo golpe", "RASEEEEGAN", new AuthorDTO(naruto));
        Post post3 = new Post(null, sdf.parse("03/03/2021"), "Konoha", "Cansei de konoha, quero minha vingança", new AuthorDTO(sasuke));
        Post post4 = new Post(null, sdf.parse("03/03/2021"), "Amor", "o @sasuke não me nota", new AuthorDTO(sakura));

        CommentDTO comment1 = new CommentDTO("Voce é burro demais", sdf.parse("04/03/2021"), new AuthorDTO(sasuke));
        CommentDTO comment2 = new CommentDTO("Cala a boca moleque", sdf.parse("04/03/2021"), new AuthorDTO(sakura));
        CommentDTO comment3 = new CommentDTO("sasuke-kun <3", sdf.parse("04/03/2021"), new AuthorDTO(sakura));
        CommentDTO comment4 = new CommentDTO("Eu te noto @sakura", sdf.parse("04/03/2021"), new AuthorDTO(naruto));

        post1.getComments().addAll(Arrays.asList(comment1, comment2));
        post3.getComments().add(comment3);
        post4.getComments().add(comment4);

        postRepository.insert((Arrays.asList(post1, post2, post3, post4)));

        naruto.getPosts().addAll(Arrays.asList(post1, post2));
        sasuke.getPosts().add(post3);
        sakura.getPosts().add(post4);

        userRepository.saveAll(Arrays.asList(naruto, sasuke, sakura));

    }
}
