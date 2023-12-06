package com.bhurb.payments.infra.repos;

import com.bhurb.payments.domain.model.entities.products.Book;
import com.bhurb.payments.domain.repos.BookRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringBookJpaRepository extends BookRepository, JpaRepository<Book, Long> {
}
