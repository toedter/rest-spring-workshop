package com.toedter.workshops.springrest.lab5.movie;

import com.toedter.spring.hateoas.jsonapi.JsonApiModelBuilder;
import com.toedter.workshops.springrest.lab5.director.Director;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.toedter.spring.hateoas.jsonapi.JsonApiModelBuilder.jsonApiModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.afford;

@Component
@Slf4j
class MovieModelAssembler {

    private static final String DIRECTORS = "directors";

    public RepresentationModel<?> toModel(Movie movie, boolean withAffordances) {
        Link selfLink = linkTo(methodOn(MovieController.class).findOne(movie.getId())).withSelfRel();
        Link directorsLink = linkTo(methodOn(MovieController.class).findDirectors(movie.getId())).withRel("directors");

        String relationshipSelfLink = selfLink.getHref() + "/relationships/" + DIRECTORS;
        String relationshipRelatedLink = selfLink.getHref() + "/" + DIRECTORS;

        final Affordance updatePartiallyAffordance =
                afford(methodOn(MovieController.class).updateMoviePartially(EntityModel.of(movie), movie.getId()));

        final Affordance deleteAffordance =
                afford(methodOn(MovieController.class).deleteMovie(movie.getId()));

        Link link;
        if(withAffordances) {
            link = selfLink.andAffordance(updatePartiallyAffordance).andAffordance(deleteAffordance);
        } else {
            link = selfLink;
        }

        JsonApiModelBuilder builder = jsonApiModel()
                .model(movie)
                .link(link);

        builder = builder
                .relationship(DIRECTORS, movie.getDirectors())
                .relationship(DIRECTORS, relationshipSelfLink, relationshipRelatedLink, null);

        return builder.build();
    }

    public RepresentationModel<?> directorsToModel(Movie movie) {
        Link selfLink = linkTo(methodOn(MovieController.class).findDirectors(movie.getId())).withSelfRel();

        RepresentationModel<?> directors = CollectionModel.of(movie.getDirectors());
        directors.add(selfLink);

        return directors;
    }
}
