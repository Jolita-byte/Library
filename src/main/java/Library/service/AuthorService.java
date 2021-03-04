package Library.service;

import Library.entity.Author;
import Library.repository.Author.AuthorRepository;

import java.util.List;

public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveNewAuthor(String firstName, String lastName) {
        Author author = new Author(firstName, lastName);
        authorRepository.save(author);
        return author;
    }

    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

//    public List<Author> findAuthorsByNameFragment(String fragment) {
////        return authorRepository.searchByNameFragment(fragment);
//    }
}
