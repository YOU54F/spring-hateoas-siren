/*-
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.ingogriebsch.spring.hateoas.siren;

import static com.google.common.collect.Lists.newArrayList;
import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Representation of a Siren embedded representation.
 * 
 * @author Ingo Griebsch
 */
@AllArgsConstructor
@EqualsAndHashCode
@FieldDefaults(makeFinal = false, level = PRIVATE)
@Getter
@NoArgsConstructor
@ToString
class SirenEmbeddedRepresentation {

    @NonNull
    RepresentationModel<?> model;
    List<LinkRelation> rels;

    SirenEmbeddedRepresentation(@NonNull RepresentationModel<?> model) {
        this.model = model;
    }

    SirenEmbeddedRepresentation(@NonNull RepresentationModel<?> model, @NonNull String rel) {
        this(model, LinkRelation.of(rel));
    }

    SirenEmbeddedRepresentation(@NonNull RepresentationModel<?> model, @NonNull LinkRelation rel) {
        this(model, newArrayList(rel));
    }
}