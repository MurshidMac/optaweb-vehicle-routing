/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaweb.vehiclerouting.domain;

import java.math.BigDecimal;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

public class LocationTest {

    @Test
    public void constructor_params_must_not_be_null() {
        assertThatNullPointerException().isThrownBy(() -> new Location(0, null, ""));
        assertThatNullPointerException().isThrownBy(() -> new Location(0, LatLng.valueOf(1, 1), null));
    }

    @Test
    public void locations_are_equal_only_if_they_have_same_id_coordinate_and_description() {
        LatLng latLng0 = new LatLng(BigDecimal.ZERO, BigDecimal.ZERO);
        LatLng latLng1 = new LatLng(BigDecimal.ONE, BigDecimal.ONE);
        String description = "d e s c r i p t i o n";

        final Location location = new Location(0, latLng0, description);

        // different ID
        assertThat(location).isNotEqualTo(new Location(1, latLng0, description));
        // different coordinate
        assertThat(location).isNotEqualTo(new Location(0, latLng1, description));
        // different description
        assertThat(location).isNotEqualTo(new Location(0, latLng0, "xyz"));
        // null
        assertThat(location).isNotEqualTo(null);
        // different class
        assertThat(location).isNotEqualTo(latLng0);
        // same object -> OK
        assertThat(location).isEqualTo(location);
        // same properties -> OK
        assertThat(location).isEqualTo(new Location(0, latLng0, description));
    }

    @Test
    public void constructor_without_description_should_create_empty_description() {
        long id = 7;
        LatLng latLng = LatLng.valueOf(3.14, 4.13);
        assertThat(new Location(id, latLng)).isEqualTo(new Location(id, latLng, ""));
    }
}
