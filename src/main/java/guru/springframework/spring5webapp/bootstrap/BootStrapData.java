package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository){
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher pub1 = Publisher.builder().name("NameAd1").addressLine1("Line1").city("qc").state("qc").zip("G1H1M1").build();
        publisherRepository.save(pub1);

        Author eric = Author.builder().firstName("Eric").lastName("Smith").build();
        Book book1 = Book.builder().title("Domain Driveen Design").isbn("12345").build();
        eric.setBooks(new HashSet<>(Arrays.asList(book1)));
        book1.setAuthors(new HashSet<>(Arrays.asList(eric)));

        book1.setPublisher(pub1);
        pub1.setBooks(new HashSet<>(Arrays.asList(book1)));

        authorRepository.save(eric);
        bookRepository.save(book1);
        publisherRepository.save(pub1);

        Author john = Author.builder().firstName("John").lastName("Lan").build();
        Book book2 = Book.builder().title("Old JB").isbn("12589").build();
        john.setBooks(new HashSet<>(Arrays.asList(book2)));
        book2.setAuthors(new HashSet<>(Arrays.asList(john)));

        book2.setPublisher(pub1);
        pub1.getBooks().add(book2);

        authorRepository.save(john);
        bookRepository.save(book2);
        publisherRepository.save(pub1);


        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("Publisher number of bokkes: " + pub1.getBooks().size());


    }
}
