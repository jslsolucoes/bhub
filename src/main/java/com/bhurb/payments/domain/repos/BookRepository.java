package com.bhurb.payments.domain.repos;

import com.bhurb.payments.domain.model.entities.products.Book;

import java.util.Optional;

public interface BookRepository {
    Optional<Book> findById(final Long id);
}
