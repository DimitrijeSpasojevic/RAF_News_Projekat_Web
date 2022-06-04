package rs.raf.rafnewsprojekatweb;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import rs.raf.rafnewsprojekatweb.repositories.categories.CategoriesRepository;
import rs.raf.rafnewsprojekatweb.repositories.categories.MySqlCategoriesRepository;
import rs.raf.rafnewsprojekatweb.repositories.comments.CommentRepository;
import rs.raf.rafnewsprojekatweb.repositories.comments.MySqlCommentRepository;
import rs.raf.rafnewsprojekatweb.repositories.news.MySqlNewsRepository;
import rs.raf.rafnewsprojekatweb.repositories.news.NewsRepository;
import rs.raf.rafnewsprojekatweb.repositories.user.MySqlUserRepository;
import rs.raf.rafnewsprojekatweb.repositories.user.UserRepository;
import rs.raf.rafnewsprojekatweb.services.CategoryService;
import rs.raf.rafnewsprojekatweb.services.CommentService;
import rs.raf.rafnewsprojekatweb.services.NewsService;
import rs.raf.rafnewsprojekatweb.services.UserService;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {

    public HelloApplication() {
        // Ukljucujemo validaciju
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Definisemo implementacije u dependency container-u
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(MySqlUserRepository.class).to(UserRepository.class).in(Singleton.class);
                this.bind(MySqlCommentRepository.class).to(CommentRepository.class).in(Singleton.class);
                this.bind(MySqlNewsRepository.class).to(NewsRepository.class).in(Singleton.class);
                this.bind(MySqlCategoriesRepository.class).to(CategoriesRepository.class).in(Singleton.class);

                this.bindAsContract(UserService.class);
                this.bindAsContract(CommentService.class);
                this.bindAsContract(NewsService.class);
                this.bindAsContract(CategoryService.class);
            }
        };
        register(binder);


        // Ucitavamo resurse
        packages("rs.raf.rafnewsprojekatweb");
    }
}