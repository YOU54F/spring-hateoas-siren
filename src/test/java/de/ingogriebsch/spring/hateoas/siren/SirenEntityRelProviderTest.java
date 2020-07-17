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
package de.ingogriebsch.spring.hateoas.siren;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.hateoas.IanaLinkRelations.ITEM;

import org.junit.jupiter.api.Test;
import org.springframework.hateoas.RepresentationModel;

class SirenEntityRelProviderTest {

    private static final SirenEntityRelProvider provider = new SirenEntityRelProvider() {
    };

    @Test
    void get_should_return_no_rel_if_parent_is_null() {
        assertThat(provider.get(new RepresentationModel<>(), null)).isEmpty();
    }

    @Test
    void get_should_return_single_rel_if_parent_is_not_null() {
        assertThat(provider.get(new RepresentationModel<>(), new RepresentationModel<>())).containsExactly(ITEM);
    }
}
