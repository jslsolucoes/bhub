package com.bhurb.payments.domain.repos;


import com.bhurb.payments.domain.model.entities.products.Video;

import java.util.Optional;

public interface VideoRepository {
    Optional<Video> findById(final Long id);
}
