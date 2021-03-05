package Library.service;

import Library.entity.Author;
import Library.repository.Author.AuthorRepository;

import java.util.List;
import java.util.Set;

public class AuthorService {

    private final AuthorRepository authorRepository;


    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveNewAuthor(String name, String surname) {
        Author author = new Author(name, surname);
        authorRepository.save(author);
        return author;
    }

    public Set<Author> findAllAuthors() {
        return (Set<Author>) authorRepository.findAll();
    }

    public Author updateAuthor(Author author) {
        authorRepository.save(author);
        return author;
    }

    public List<Author> findAuthorsByKeyWord(String keyWord) {
        return authorRepository.searchByKeyWord(keyWord);
    }
}
